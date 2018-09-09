package eu.szwiec.mapssample.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import eu.szwiec.checkittravelkit.repository.local.CoordinatesDao
import eu.szwiec.mapssample.R
import eu.szwiec.mapssample.api.ApiErrorResponse
import eu.szwiec.mapssample.api.ApiSuccessResponse
import eu.szwiec.mapssample.api.ZomatoService
import eu.szwiec.mapssample.data.Coordinates
import eu.szwiec.mapssample.data.Place
import eu.szwiec.mapssample.location.LocationLiveData
import eu.szwiec.mapssample.util.AppExecutors
import timber.log.Timber

interface Repository {
    fun getNearbyRestaurants(): LiveData<List<Place>>
}

class RepositoryImpl(private val context: Context, private val location: LocationLiveData, private val zomatoService: ZomatoService, private val dao: CoordinatesDao, private val appExecutors: AppExecutors): Repository {
    override fun getNearbyRestaurants(): LiveData<List<Place>> {

        val result = MediatorLiveData<List<Place>>()

        result.addSource(location) {
            Timber.d("location update")

            val key = context.getString(R.string.zomato_key)
            val coordinates = Coordinates(it.latitude, it.longitude, System.currentTimeMillis())
            appExecutors.diskIO().execute { dao.insert(coordinates) }

            val apiSource = zomatoService.nearbyRestaurants(key, it.latitude, it.longitude)

            result.addSource(apiSource) { response ->
                Timber.d("API update")

                when (response) {
                    is ApiSuccessResponse -> {
                        val places = response.body
                        result.value = places
                    }
                    is ApiErrorResponse -> {
                        result.value = emptyList()
                    }
                }
            }
        }

        result.addSource(dao.getAll()) {
            Timber.d("coordinates from DB = %s", it)
        }

        return result
    }
}
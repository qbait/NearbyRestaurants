package eu.szwiec.mapssample.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import eu.szwiec.mapssample.R
import eu.szwiec.mapssample.api.ApiErrorResponse
import eu.szwiec.mapssample.api.ApiSuccessResponse
import eu.szwiec.mapssample.api.ZomatoService
import eu.szwiec.mapssample.data.Place
import eu.szwiec.mapssample.location.BoundLocationManager
import timber.log.Timber

interface Repository {
    fun getNearbyRestaurants(): LiveData<List<Place>>
}

class RepositoryImpl(private val context: Context, private val boundLocationManager: BoundLocationManager, private val zomatoService: ZomatoService): Repository {
    override fun getNearbyRestaurants(): LiveData<List<Place>> {

        val result = MediatorLiveData<List<Place>>()

        result.addSource(boundLocationManager) {

            Timber.d("location update")
            Toast.makeText(context, "location update", Toast.LENGTH_SHORT).show()

            val key = context.getString(R.string.zomato_key)
            val apiSource = zomatoService.nearbyRestaurants(key, it.latitude, it.longitude)

            result.addSource(apiSource) { response ->

                Toast.makeText(context, "API update", Toast.LENGTH_SHORT).show()
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

        return result
    }

}
package eu.szwiec.mapssample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import eu.szwiec.mapssample.api.ApiErrorResponse
import eu.szwiec.mapssample.api.ApiSuccessResponse
import eu.szwiec.mapssample.api.ZomatoService
import eu.szwiec.mapssample.data.Place

interface Repository {
    fun getNearbyRestaurants(key: String, lat: Double, lon: Double): LiveData<List<Place>>
}

class RepositoryImpl(private val zomatoService: ZomatoService): Repository {
    override fun getNearbyRestaurants(key: String, lat: Double, lon: Double): LiveData<List<Place>> {

        val result = MediatorLiveData<List<Place>>()
        val apiSource = zomatoService.nearbyRestaurants(key, lat, lon)

        result.addSource(apiSource) { response ->
            result.removeSource(apiSource)

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

        return result
    }

}
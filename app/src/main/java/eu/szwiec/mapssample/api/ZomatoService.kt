package eu.szwiec.mapssample.api

import androidx.lifecycle.LiveData
import eu.szwiec.mapssample.data.Place
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ZomatoService {

    @GET("geocode")
    fun nearbyRestaurants(
            @Header("user-key") key: String,
            @Query("lat") lat: Double,
            @Query("lon") lon: Double
    ): LiveData<ApiResponse<List<Place>>>

}
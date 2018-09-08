package eu.szwiec.mapssample.api

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import eu.szwiec.mapssample.data.Place

class MockZomatoService: ZomatoService {
    override fun nearbyRestaurants(key: String, lat: Double, lon: Double): LiveData<ApiResponse<List<Place>>> {
        val places = listOf(Place("Sample Restaurant", "Trill Mill Court, OX1 1DG", LatLng(0.0, 0.0)))

        val ld = MutableLiveData<ApiResponse<List<Place>>>()
        val apiResponse = ApiSuccessResponse(places)
        Handler().postDelayed({ ld.postValue(apiResponse) }, 1000)
        return ld
    }

}
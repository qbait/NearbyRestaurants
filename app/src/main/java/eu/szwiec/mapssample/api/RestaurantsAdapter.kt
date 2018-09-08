package eu.szwiec.mapssample.api

import com.google.android.gms.maps.model.LatLng
import com.squareup.moshi.FromJson
import eu.szwiec.mapssample.data.NearbyRestaurant
import eu.szwiec.mapssample.data.Place
import eu.szwiec.mapssample.data.ZomatoResults
import timber.log.Timber

class RestaurantsAdapter {

    @FromJson
    fun fromJson(zomatoResults: ZomatoResults): List<Place> {
        try {
            return zomatoResults.nearby_restaurants.map { translate(it) }
        } catch (e: Exception) {
            Timber.e("Parsing Visa requirements error: %s", e)
            return emptyList()
        }
    }

    private fun translate(nearbyRestaurant: NearbyRestaurant) : Place {
        return Place(
                name = nearbyRestaurant.restaurant.name,
                address = nearbyRestaurant.restaurant.location.address,
                latLng = LatLng(nearbyRestaurant.restaurant.location.latitude.toDouble(), nearbyRestaurant.restaurant.location.longitude.toDouble())
        )
    }

}
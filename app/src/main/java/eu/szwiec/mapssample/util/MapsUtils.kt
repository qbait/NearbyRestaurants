package eu.szwiec.mapssample.util

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import eu.szwiec.mapssample.data.Place

fun GoogleMap.display(places: List<Place>): List<Marker> {
    zoom(places)
    return addMarkers(places)
}

fun GoogleMap.animateCamera(marker: Marker) {
    animateCamera(CameraUpdateFactory.newLatLng(marker.position))
}

private fun GoogleMap.addMarkers(places: List<Place>) : List<Marker> {
    val markers = mutableListOf<Marker>()
    places.forEach {
        val marker = addMarker(MarkerOptions().position(it.latLng).title(it.name))
        markers.add(marker)
    }

    return markers
}

private fun GoogleMap.zoom(places: List<Place>) {
    val locations = places.map { it.latLng }
    val boundsBuilder = LatLngBounds.Builder()
    for (latLngPoint in locations)
        boundsBuilder.include(latLngPoint)

    val routePadding = 200
    val latLngBounds = boundsBuilder.build()

    this.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, routePadding))
}
package eu.szwiec.mapssample.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class LocationLiveData(context: Context, interval: Long = 10000, fastestInterval: Long = 5000, priority: Int = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, smallestDisplacement: Float = 10f) : LiveData<Location>() {
    private val mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val mLocationRequest = LocationRequest().apply {
        this.interval = interval
        this.fastestInterval = fastestInterval
        this.priority = priority
        this.smallestDisplacement = smallestDisplacement
    }

    private var mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            for (location in locationResult.locations) {
                value = location
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        mFusedLocationClient.removeLocationUpdates(mLocationCallback)
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null)
    }

}
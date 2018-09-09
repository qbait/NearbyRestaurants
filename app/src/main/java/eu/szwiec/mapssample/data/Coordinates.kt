package eu.szwiec.mapssample.data

import androidx.room.Entity

@Entity(primaryKeys = ["timestamp"])
data class Coordinates(
        val lat: Double,
        val lng: Double,
        val timestamp: Long
)
package eu.szwiec.checkittravelkit.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import eu.szwiec.mapssample.data.Coordinates

@Dao
interface CoordinatesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coordinates: Coordinates)

    @Query("SELECT * FROM coordinates")
    fun getAll(): LiveData<List<Coordinates>>
}
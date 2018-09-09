package eu.szwiec.checkittravelkit.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eu.szwiec.mapssample.data.Coordinates
import eu.szwiec.mapssample.util.DATABASE_NAME

@Database(entities = [Coordinates::class], version = 1)
abstract class CoordinatesDb : RoomDatabase() {

    abstract fun dao(): CoordinatesDao

    companion object {

        @Volatile
        private var instance: CoordinatesDb? = null

        fun getInstance(context: Context): CoordinatesDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CoordinatesDb {
            return Room.databaseBuilder(context, CoordinatesDb::class.java, DATABASE_NAME)
                    .build()
        }
    }
}
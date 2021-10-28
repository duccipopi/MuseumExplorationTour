package br.duccipopi.met.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.duccipopi.met.model.Artwork
import br.duccipopi.met.model.Department

@Database(entities = [Department::class, Artwork::class], version = 1, exportSchema = false)
abstract class MetMuseumDatabase: RoomDatabase() {

    abstract val metMuseumDao: MetMuseumDao

    companion object {
        @Volatile
        private var INSTANCE: MetMuseumDatabase? = null

        fun getInstance(context: Context):MetMuseumDatabase {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MetMuseumDatabase::class.java,
                    "met_museum_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }

}
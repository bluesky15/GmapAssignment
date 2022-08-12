package com.lkb.googlemapdemo

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Place::class], version = 1)
abstract class MapDataBase : RoomDatabase() {
    abstract fun mapDao(): MapDAO

    companion object {
        @Volatile
        private var INSTANCE: MapDataBase? = null

        fun getDataBase(context: Application, scope: CoroutineScope): MapDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MapDataBase::class.java,
                    "map_database"
                )
                    // .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }
}
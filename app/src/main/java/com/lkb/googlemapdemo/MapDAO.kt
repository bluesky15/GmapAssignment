package com.lkb.googlemapdemo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MapDAO {
    @Query("SELECT * FROM PLACE")
    suspend fun getPlaceData(): List<Place>

    @Insert
    suspend fun insertPlaceData(place: Place)

    @Update
    suspend fun updatePlaceData(place: Place)

    @Delete
    suspend fun deletePlaceData(place: Place)
}
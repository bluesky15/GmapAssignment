package com.lkb.googlemapdemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "place")
data class Place(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "placeName")
    val placeName: String,
    @ColumnInfo(name = "lat")
    val latitude: String,
    @ColumnInfo(name = "long")
    val longitude: String
)
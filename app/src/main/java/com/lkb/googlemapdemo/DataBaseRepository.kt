package com.lkb.googlemapdemo

class DataBaseRepository(private val dataBase: MapDAO) {
     suspend fun saveData(place: Place) {
        dataBase.insertPlaceData(place)
    }

   suspend fun getData(): List<Place> {
       return dataBase.getPlaceData()
    }
}

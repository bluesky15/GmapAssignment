package com.lkb.googlemapdemo

class DataBaseRepository(val dataBase: MapDAO) {
     suspend fun saveData(place: Place) {
        dataBase.insertPlaceData(place)
    }

   suspend fun getData(): List<Place> {
       return dataBase.getPlaceData()
    }
}

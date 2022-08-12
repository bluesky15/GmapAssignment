package com.lkb.googlemapdemo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MapViewModel(private val repository: DataBaseRepository) : ViewModel() {
    val _mapData: MutableLiveData<List<Place>> = MutableLiveData()
    val mapData get() = _mapData

    fun saveData(lat: String?, long: String?) {
        Log.d(">>>", "data saaved to the Database")
        viewModelScope.launch {
            repository.saveData(
                Place(
                    placeName = "",
                    latitude = lat ?: "0",
                    longitude = long ?: "0"
                )
            )
        }

    }

    fun getData(): MutableLiveData<List<Place>> {
        viewModelScope.launch {
            val list = repository.getData()
            Log.d(">>>", "$list")
            _mapData.value = list
        }
        return mapData
    }

}

class MapViewModelFactory(private val repository: DataBaseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MapViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

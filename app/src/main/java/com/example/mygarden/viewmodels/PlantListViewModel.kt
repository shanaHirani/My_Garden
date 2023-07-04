package com.example.mygarden.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarden.api.PlantService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantListViewModel @Inject constructor ( private val Service: PlantService):ViewModel() {

    init {
        viewModelScope.launch {
            val a = Service.searchPhotos("",1,2)
            Log.i("ssssss",a.totalPage.toString())
        }
    }

    fun a():String {
        return "11"
    }

}
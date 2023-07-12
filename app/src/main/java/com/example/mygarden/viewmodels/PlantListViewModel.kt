package com.example.mygarden.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarden.data.model.domainModel.PlantSearchResult
import com.example.mygarden.data.remot.remoteModel.PlantSearchResultDto
import com.example.mygarden.data.repository.PlantRepository
import com.example.mygarden.shared.Event
import com.example.mygarden.shared.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class ApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class PlantListViewModel @Inject constructor ( private val plantRepository: PlantRepository):ViewModel() {

    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus>
        get() = _apiStatus


    private val _plantSearchResult = MutableLiveData<PlantSearchResult?>()
    val plantSearchResult: LiveData<PlantSearchResult?>
        get() = _plantSearchResult

    private val _apiError = MutableLiveData<Event<NetworkResponse.Failure>>()

    init {
        viewModelScope.launch {
            getPlant()
        }
    }

    private suspend fun getPlant() {
        _apiStatus.value = ApiStatus.LOADING
        val result = plantRepository.getPlant()
        if (result is NetworkResponse.Success) {
            _apiStatus.value = ApiStatus.DONE
            _plantSearchResult.value = result.value
            Log.i("ssssss", plantSearchResult.value!!.totalPages.toString())
        }
        if (result is NetworkResponse.Failure) {
            _apiStatus.value = ApiStatus.ERROR
            _apiError.value = Event(result)
            _apiError.value!!.getContentIfNotHandled()?.toString()?.let { Log.i("sssssse", it) }
        }
    }

    fun a():String {
        return "11"
    }

}
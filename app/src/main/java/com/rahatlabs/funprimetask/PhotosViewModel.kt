package com.rahatlabs.funprimetask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahatlabs.funprimetask.model.Photo
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> get() = _photos

    init {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getPhotos()
            _photos.postValue(response)
        }
    }
}

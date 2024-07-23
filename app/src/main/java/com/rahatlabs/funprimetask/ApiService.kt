package com.rahatlabs.funprimetask

import com.rahatlabs.funprimetask.model.Photo
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}

package com.example.mvvmwithretrofit.repo

import com.example.mvvmwithretrofit.retrofit.RetrofitService
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofitService: RetrofitService) {
    fun getPhotos() = retrofitService.getPhotos()
}
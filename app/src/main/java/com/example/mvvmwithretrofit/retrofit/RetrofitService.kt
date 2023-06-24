package com.example.mvvmwithretrofit.retrofit

import com.example.mvvmwithretrofit.models.Image
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Singleton

interface RetrofitService {
    @GET("photos")
    fun getPhotos(): Call<List<Image>>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }

    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule1 {
        @Provides
        @Singleton
        fun providerDemo(): RetrofitService = retrofitService!!
    }


}

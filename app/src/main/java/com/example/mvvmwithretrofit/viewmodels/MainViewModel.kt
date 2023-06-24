package com.example.mvvmwithretrofit.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmwithretrofit.models.Image
import com.example.mvvmwithretrofit.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val photosList = MutableLiveData<List<Image>>()
    val errorMessage = MutableLiveData<String>()
    val showLoader=MutableLiveData(false)
    fun getPhotos() {
        showLoader.value=true
        val response = repository.getPhotos()
        response.enqueue(object : retrofit2.Callback<List<Image>> {
            override fun onResponse(call: Call<List<Image>>, response: Response<List<Image>>) {
                photosList.postValue(response.body())
                showLoader.value=false
            }

            override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                errorMessage.postValue(t.message)
                showLoader.value=false
            }

        })
    }
}
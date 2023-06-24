package com.example.mvvmwithretrofit.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mvvmwithretrofit.models.Image
import com.example.mvvmwithretrofit.repo.MainRepository
import com.example.mvvmwithretrofit.viewmodels.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response

class MainViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MainRepository

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(repository)
    }

    @Test
    fun getPhotos_success_response() {
        val photos = listOf(Image(1, 1, "test", "", ""), Image(2, 2, "test", "", ""))

        val mockCall: Call<List<Image>> = mock(Call::class.java) as Call<List<Image>>
        val mockResponse: Response<List<Image>> = Response.success(photos)

        `when`(repository.getPhotos()).thenReturn(mockCall)
        `when`(mockCall.enqueue(any())).thenAnswer {
            val callback = it.getArgument<retrofit2.Callback<List<Image>>>(0)
            callback.onResponse(mockCall, mockResponse)
        }

        viewModel.getPhotos()

        assertEquals(photos, viewModel.photosList.value)
        assertEquals(null, viewModel.errorMessage.value)
    }

    @Test
    fun getPhotos_failure_response() {
        val errorMessage = "Error fetching photos"
        val mockCall: Call<List<Image>> = mock(Call::class.java) as Call<List<Image>>
        val mockThrowable: Throwable = Throwable(errorMessage)

        `when`(repository.getPhotos()).thenReturn(mockCall)
        `when`(mockCall.enqueue(any())).thenAnswer {
            val callback = it.getArgument<retrofit2.Callback<List<Image>>>(0)
            callback.onFailure(mockCall, mockThrowable)
        }

        viewModel.getPhotos()

        assertEquals(null, viewModel.photosList.value)
        assertEquals(errorMessage, viewModel.errorMessage.value)
    }
}

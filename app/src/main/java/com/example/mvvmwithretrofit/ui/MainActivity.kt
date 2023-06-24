package com.example.mvvmwithretrofit.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mvvmwithretrofit.*
import com.example.mvvmwithretrofit.adapters.PhotosAdapter
import com.example.mvvmwithretrofit.databinding.ActivityMainBinding
import com.example.mvvmwithretrofit.models.Image
import com.example.mvvmwithretrofit.retrofit.RetrofitService
import com.example.mvvmwithretrofit.utils.OnItemClickListener
import com.example.mvvmwithretrofit.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val retrofitService = RetrofitService.getInstance()
    lateinit var adapter: PhotosAdapter
    //view binding
    private val listener = object : OnItemClickListener<Image> {
        override fun onItemClick(item: Image) {
            startActivity(
                Intent(
                    this@MainActivity,
                    ImageDetailActivity::class.java
                ).putExtra("photos", item)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = PhotosAdapter(listener)
        binding.recyclerview.adapter = adapter

        viewModel.photosList.observe(this, Observer {
            adapter.setPhotos(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG)

        })

        viewModel.getPhotos()

    }
}
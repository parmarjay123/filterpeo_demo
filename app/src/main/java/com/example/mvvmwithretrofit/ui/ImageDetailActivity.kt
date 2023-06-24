package com.example.mvvmwithretrofit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mvvmwithretrofit.R
import com.example.mvvmwithretrofit.databinding.ActivityPhotoDetailBinding
import com.example.mvvmwithretrofit.models.Image
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoDetailBinding

    //data binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_detail)
        val image = intent.getSerializableExtra("photos") as? Image
        binding.data = image
        binding.backButton.setOnClickListener {
            finish()
        }

        if (image != null) {
            Picasso.get().load(image.thumbnailUrl).into(binding.imageView)
        }
    }

}
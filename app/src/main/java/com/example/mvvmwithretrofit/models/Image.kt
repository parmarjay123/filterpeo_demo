package com.example.mvvmwithretrofit.models

import java.io.Serializable

data class Image(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Serializable {
    // Image class implementation
}
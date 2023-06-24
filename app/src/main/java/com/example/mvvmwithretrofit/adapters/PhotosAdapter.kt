package com.example.mvvmwithretrofit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmwithretrofit.databinding.ItemPhotosBinding
import com.example.mvvmwithretrofit.models.Image
import com.example.mvvmwithretrofit.utils.OnItemClickListener
import com.squareup.picasso.Picasso

class PhotosAdapter(val listener: OnItemClickListener<Image>) :
    RecyclerView.Adapter<MainViewHolder>() {

    var photosList = mutableListOf<Image>()

    fun setPhotos(photos: List<Image>) {
        this.photosList = photos.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemPhotosBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = photosList[position]
        holder.binding.tvTitle.text = item.title
        Picasso.get().load(item.thumbnailUrl).into(holder.binding.ivPhotos)

        holder.binding.llMain.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return photosList.size
    }
}

class MainViewHolder(val binding: ItemPhotosBinding) : RecyclerView.ViewHolder(binding.root)

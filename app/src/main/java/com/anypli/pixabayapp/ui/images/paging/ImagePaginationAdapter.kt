package com.anypli.pixabayapp.ui.images.paging

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anypli.pixabayapp.data.model.Hits
import com.bumptech.glide.RequestManager

class ImagePaginationAdapter(private val requestManager: RequestManager):
    PagingDataAdapter<Hits, RecyclerView.ViewHolder>(hitsDiffCallback){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder.create(parent,requestManager)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageViewHolder).bind(getItem(position)!!)
    }

    companion object{
        val hitsDiffCallback = object : DiffUtil.ItemCallback<Hits>(){
            override fun areItemsTheSame(oldItem: Hits, newItem: Hits): Boolean {
                return oldItem.id== newItem.id

            }

            override fun areContentsTheSame(oldItem: Hits, newItem: Hits): Boolean {
                return oldItem == newItem
            }

        }

    }
}
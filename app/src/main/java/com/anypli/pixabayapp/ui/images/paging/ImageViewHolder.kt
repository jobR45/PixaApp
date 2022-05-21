package com.anypli.pixabayapp.ui.images.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anypli.pixabayapp.data.model.Hits
import com.anypli.pixabayapp.databinding.ItemListHitsBinding
import com.bumptech.glide.RequestManager

class ImageViewHolder(
    private val binding : ItemListHitsBinding,
    private val requestManager: RequestManager
)  : RecyclerView.ViewHolder(binding.root){

    fun bind(image: Hits){
        binding.image = image
        binding.requestManager = requestManager

    }

    companion object {
        fun create(
            parent: ViewGroup,
            requestManager: RequestManager
        ): ImageViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemListHitsBinding.inflate(inflater, parent, false)
            return ImageViewHolder(
                binding,
                requestManager
            )
        }
    }
}
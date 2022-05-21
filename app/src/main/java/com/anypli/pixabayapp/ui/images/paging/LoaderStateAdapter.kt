package com.anypli.pixabayapp.ui.images.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anypli.pixabayapp.databinding.ItemListFooterBinding
import com.bumptech.glide.RequestManager

class LoaderStateAdapter : LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>(){

    class LoaderViewHolder(binding: ItemListFooterBinding) : RecyclerView.ViewHolder(binding.root){
        companion object {
            fun create(parent: ViewGroup): LoaderViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemListFooterBinding.inflate(inflater, parent, false)
                return LoaderViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
       //todo
       //add retry button
       //add error text
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder.create(parent)
    }
}
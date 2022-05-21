package com.anypli.pixabayapp.global.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anypli.pixabayapp.global.listener.DataAdapterListener
import com.bumptech.glide.RequestManager

/**
 * property TAG extension for Loging
 *
 */
val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

fun Context?.isNetworkAvailable(): Boolean {
    if (this == null) return false
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}




@BindingAdapter(value= ["imageUrl","placeholder","requestManager"],requireAll = true)
fun setImage(view : ImageView, imageUrl:String?, placeholder: Drawable, requestManager: RequestManager){
    if(!imageUrl.isNullOrEmpty()){
        requestManager
            .load(imageUrl)
            .placeholder(placeholder)
            .into(view)
    }


}
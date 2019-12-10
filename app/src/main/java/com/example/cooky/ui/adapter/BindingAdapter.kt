package com.example.cooky.ui.adapter

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.cooky.R

@BindingAdapter("loadImage")
fun ImageView.loadImage(uri: String?){
    Glide.with(context)
        .asBitmap()
        .load(uri)
        .apply(RequestOptions().centerCrop())
        .placeholder(R.drawable.place_holder_312x231)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(this)
}

@BindingAdapter("loadBigImage")
fun ImageView.loadBigImage(uri: String?){
        if(uri != null) {
        val first = uri.indexOf('-') + 1
        val stringBuilder = StringBuilder(uri)
        stringBuilder.replace(first,first+7, BIG_IMAGE_SIZE)
        val newUri = stringBuilder.toString()
        Glide.with(context)
            .asBitmap()
            .load(newUri)
            .placeholder(R.drawable.place_holder_636x393)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(this)
    }
}

@BindingAdapter("loadBackground")
fun View.loadBackground(uri: String?) {
    Glide.with(context)
        .asBitmap()
        .load(uri)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    this@loadBackground.background = BitmapDrawable(resources, resource)
                }
            }
        })
}

const val NORMAL_IMAGE_SIZE = "312x231"
const val BIG_IMAGE_SIZE = "636x393"

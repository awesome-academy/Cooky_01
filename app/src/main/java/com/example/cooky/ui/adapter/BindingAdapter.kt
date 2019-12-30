package com.example.cooky.ui.adapter

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.cooky.R
import com.example.cooky.data.local.model.recipe.ExtendedIngredient

@BindingAdapter("loadImage")
fun ImageView.loadImage(uri: String?) {
    Glide.with(context)
        .asBitmap()
        .load(uri)
        .apply(RequestOptions().centerCrop())
        .placeholder(R.drawable.place_holder_312x231)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(this)
}

@BindingAdapter("loadBigImage")
fun ImageView.loadBigImage(uri: String?) {
    if (uri != null) {
        val first = uri.indexOf('-') + 1
        val stringBuilder = StringBuilder(uri)
        stringBuilder.replace(first, first + 7, BIG_IMAGE_SIZE)
        val newUri = stringBuilder.toString()
        Glide.with(context)
            .asBitmap()
            .load(newUri)
            .placeholder(R.drawable.place_holder_636x393)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(this)
    }
}

@BindingAdapter("loadBigImageByTitle")
fun ImageView.loadBigImageByTitle(imageTitle: String?) {
    val newUri = IMAGE_RECIPE_URI + imageTitle
    Glide.with(context)
        .asBitmap()
        .load(newUri)
        .placeholder(R.drawable.place_holder_636x393)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(this)
}

@BindingAdapter("loadIngredientImage")
fun ImageView.loadIngredientImage(image: String?) {
    if (image != null) {
        val uri = IMAGE_INGRADIENT_URI + image
        Glide.with(context)
            .asBitmap()
            .load(uri)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(this)
    }
}

@BindingAdapter("loadMeasuseAmount")
fun TextView.loadMeasuseAmount(ingredient: ExtendedIngredient) {
    val stringBuilder = StringBuilder("${ingredient.measures.metric.amount.toInt()}").apply {
        append(" ")
        append(ingredient.measures.metric.unitLong)
    }
    this.text = stringBuilder.toString()
}

@BindingAdapter("loadBackground")
fun View.loadBackground(uri: String?) {
    Glide.with(context)
        .asBitmap()
        .load(uri)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    this@loadBackground.background = BitmapDrawable(resources, resource)
            }
        })
}

@BindingAdapter("setVisibleIfLoading")
fun View.setVisibleIfLoading(isLoading: Boolean) {
    this.visibility = if (isLoading) View.VISIBLE else View.GONE
}

@BindingAdapter("setGoneIfLoading")
fun View.setGoneIfLoading(isLoading: Boolean) {
    this.visibility = if (isLoading) View.GONE else View.VISIBLE
}

const val NORMAL_IMAGE_SIZE = "312x231"
const val BIG_IMAGE_SIZE = "636x393"
const val IMAGE_INGRADIENT_URI = "https://spoonacular.com/cdn/ingredients_100x100/"
const val IMAGE_RECIPE_URI = "https://spoonacular.com/recipeImages/"

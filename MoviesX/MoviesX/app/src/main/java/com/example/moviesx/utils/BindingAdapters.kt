package com.example.moviesx.utils

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.moviesx.utils.Constants.BASE_URL
import timber.log.Timber

@BindingAdapter("setPoster")
fun ImageView.setPoster(image:String?){
    image?.let {
        load(it){
            crossfade(true)
            transformations(RoundedCornersTransformation(4f, 4f, 4f, 4f))
        }
    }
}

@BindingAdapter("setRating")
fun RatingBar.setRating(rating:Double){
    setRating(rating.toFloat())
}
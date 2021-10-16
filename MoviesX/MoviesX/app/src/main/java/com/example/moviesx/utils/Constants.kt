package com.example.moviesx.utils

import android.content.Context
import android.widget.Toast

object Constants {

    const val API_KEY = "ae14ee5f"
    const val BASE_URL = "http://www.omdbapi.com/"

    const val DEFAULT_ERROR_MSG = "Oops something went wrong"
    const val NO_INTERNET_MSG = "looks like you don't have an active internet connection"
}

fun Context.showToast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
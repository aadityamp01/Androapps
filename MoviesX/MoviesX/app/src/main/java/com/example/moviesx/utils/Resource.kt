package com.example.moviesx.utils

import com.example.moviesx.utils.Constants.DEFAULT_ERROR_MSG
import com.example.moviesx.utils.Constants.NO_INTERNET_MSG
import com.example.moviesx.utils.ErrorType.UNKNOWN

sealed class Resource<T>(
    open val data: T? = null,
    open val message: String = "",
    open val errorType: ErrorType = UNKNOWN
) {
    class Empty<T> : Resource<T>()
    class Loading<T> : Resource<T>()

    data class Success<T>(override val data: T? = null, override val message: String = "") :
        Resource<T>(data, message)

    data class Error<T>(
        override val message: String,
        override val data: T? = null,
        override val errorType: ErrorType = UNKNOWN
    ) : Resource<T>(data, message)
}

enum class ErrorType(val message: String) {
    UNKNOWN(DEFAULT_ERROR_MSG), NO_INTERNET(NO_INTERNET_MSG)
}

package com.example.moviesx.data.remote.dataSource

import com.example.moviesx.data.models.MovieItem
import com.example.moviesx.data.remote.ApiService
import com.example.moviesx.utils.ErrorType
import com.example.moviesx.utils.Resource
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val api: ApiService
) : MovieDataSource {

    override suspend fun searchMovie(query: String): Resource<MovieItem> = try {
        val response = api.searchMovie(query)
        if (response.isSuccessful) {
            response.body()?.let { Resource.Success(it) }
                ?: Resource.Error(message = "Data is null")
        } else Resource.Error("Movie Not Found")
    } catch (e: IOException) {
        Resource.Error(message = ErrorType.NO_INTERNET.message, errorType = ErrorType.NO_INTERNET)
    } catch (e: Exception) {
        Resource.Error(message = e.message.toString())
    }
}

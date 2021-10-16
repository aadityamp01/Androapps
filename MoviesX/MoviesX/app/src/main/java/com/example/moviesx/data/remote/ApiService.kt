package com.example.moviesx.data.remote

import com.example.moviesx.data.models.MovieItem
import com.example.moviesx.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    suspend fun searchMovie(
        @Query("t") query: String,
        @Query("apikey") api_key: String = API_KEY
    ): Response<MovieItem>
}

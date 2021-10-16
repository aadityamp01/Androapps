package com.example.moviesx.data.remote.dataSource

import com.example.moviesx.data.models.MovieItem
import com.example.moviesx.utils.Resource

interface MovieDataSource {

    suspend fun searchMovie(query: String): Resource<MovieItem>
}

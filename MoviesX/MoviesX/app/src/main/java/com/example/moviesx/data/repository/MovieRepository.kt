package com.example.moviesx.data.repository

import com.example.moviesx.data.models.MovieItem
import com.example.moviesx.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun searchMovie(query: String): Flow<Resource<MovieItem>>
}

package com.example.moviesx.data.repository

import com.example.moviesx.data.remote.dataSource.MovieDataSource
import com.example.moviesx.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieDataSource: MovieDataSource) :
    MovieRepository {

    override suspend fun searchMovie(query: String) = flow {
        emit(Resource.Loading())
        emit(movieDataSource.searchMovie(query))
    }.flowOn(Dispatchers.IO)
}

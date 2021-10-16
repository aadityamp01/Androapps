package com.example.moviesx.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesx.data.models.MovieItem
import com.example.moviesx.data.repository.MovieRepository
import com.example.moviesx.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    val movies = MutableStateFlow<Resource<MovieItem>>(Resource.Empty())

    fun onSearchButtonPressed(text: String) = viewModelScope.launch {
        movieRepository.searchMovie(text).collect {
            movies.emit(it)
        }
    }


}

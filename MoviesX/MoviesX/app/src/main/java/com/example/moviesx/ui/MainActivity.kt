package com.example.moviesx.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.moviesx.data.models.MovieItem
import com.example.moviesx.databinding.ActivityMainBinding
import com.example.moviesx.utils.Resource
import com.example.moviesx.utils.setPoster
import com.example.moviesx.utils.setRating
import com.example.moviesx.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        collectMovies()
    }

    private fun setupViews() {
        binding.searchButton.setOnClickListener {
            val searchQuery = binding.searchBar.text.toString().trim()
            if (searchQuery.isEmpty())
                showToast("Search text cannot be empty")
            else viewModel.onSearchButtonPressed(searchQuery)
        }
    }

    private fun collectMovies() = lifecycleScope.launchWhenStarted {
        viewModel.movies.collect {
            binding.progressBar.isVisible = it is Resource.Loading
            binding.MovieRating.isVisible = it is Resource.Success && it.data!=null
            Timber.d(it.data.toString())
            when (it) {
                is Resource.Error -> showToast(it.message)
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    it.data?.let { movie -> setMovieDate(movie) }
                        ?: showToast("No results found")
                }
                else -> Unit
            }
        }
    }

    private fun setMovieDate(movie: MovieItem) {
        with(binding) {
            this.posterimage.setPoster(movie.Poster)
            this.MovieTitle.text = movie.Title
            this.MovieRating.rating = movie.imdbRating.toFloat() / 2
            this.MovieYear.text = movie.Year
            this.MovieGenre.text = movie.Genre
            this.MovieDirector.text = movie.Director
            this.MovieActors.text = movie.Actors
            this.Plot.text = movie.Plot
        }
    }
}

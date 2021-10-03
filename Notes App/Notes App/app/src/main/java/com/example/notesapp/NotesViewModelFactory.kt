package com.example.notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class NotesViewModelFactory(private val repository: NotesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NotesViewModel :: class.java)) {
            return NotesViewModel(repository) as T
        }
        throw IllegalArgumentException("Error in creating NotesViewModel!")
    }
}
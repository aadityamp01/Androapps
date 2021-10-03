package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesViewModel(private val repository: NotesRepository ) : ViewModel() {

    val notes = repository.notes
    private var isupdate = false
    private lateinit var notesToUpdateOrDelete : NotesEntity

    val inputNotesTitle  = MutableLiveData<String?>()
    val inputNote = MutableLiveData<String?>()

    val save_or_update = MutableLiveData<String>()
    val delete_or_clear = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
        get() = statusMessage

    fun saveUpdate() {

        if(inputNotesTitle.value == null && inputNote.value == null){
            statusMessage.value = Event("Please add a note!")
        }
        else if(inputNotesTitle.value == null) {
            statusMessage.value = Event("Please enter note title!")
        }
        else if(inputNote.value == null) {
            statusMessage.value = Event("Please add a note!")
        }
        else {

            if (isupdate) {
            notesToUpdateOrDelete.noteTitle = inputNotesTitle.value!!
            notesToUpdateOrDelete.noteSub = inputNote.value!!
                update(notesToUpdateOrDelete)
            } else {
                insert(NotesEntity(0, inputNotesTitle.value!!, inputNote.value!!))
                inputNotesTitle.value = null
                inputNote.value = ""
            }
        }

    }

    fun clearAllOrDelete() {
        if(isupdate) {
            delete(notesToUpdateOrDelete)
        }
        else {
            clearAll()
        }
    }
    fun insert(notesEntity: NotesEntity) {
        viewModelScope.launch {
            val inserted = repository.insert(notesEntity)
            if(inserted > -1)
            statusMessage.value = Event("Note added successfully")
            else
                statusMessage.value = Event("Error!")

        }
    }
    fun delete(notesEntity: NotesEntity) {
        viewModelScope.launch {
            inputNotesTitle.value = null
            inputNote.value = null
            isupdate = false
            save_or_update.value = "Save"
            delete_or_clear.value = "Clear All"
            val delete = repository.delete(notesEntity)
            if(delete>0)
                statusMessage.value = Event("$delete note deleted successfully")
            else
                statusMessage.value = Event("Error in deleting notes!")
        }
    }
    fun update(notesEntity: NotesEntity) {
        viewModelScope.launch {
            val updated = repository.update(notesEntity)
            inputNotesTitle.value = ""
            inputNote.value = ""
            isupdate = false
            save_or_update.value = "Save"
            delete_or_clear.value = "Clear All"
            if(updated > 0)
                statusMessage.value = Event("Note updated successfully")
            else
                statusMessage.value = Event("Error in updating notes!")
        }
    }
    fun clearAll() {
        viewModelScope.launch {
            val delete = repository.deleteAll()
            if(delete>0)
                statusMessage.value = Event("$delete notes deleted successfully")
            else
                statusMessage.value = Event("No notes available!")

        }
    }
    init {
        save_or_update.value = "Save"
        delete_or_clear.value = "Clear All"
    }
    fun initUpdateOrDelete(notesEntity: NotesEntity) {
        inputNotesTitle.value = notesEntity.noteTitle
        inputNote.value = notesEntity.noteSub
        isupdate = true
        notesToUpdateOrDelete = notesEntity
        save_or_update.value = "Update"
        delete_or_clear.value = "Delete"
    }
}
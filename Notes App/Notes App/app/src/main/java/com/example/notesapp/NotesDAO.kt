package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO  {

    @Insert
    suspend fun insertNotesDAO(notesEntity : NotesEntity) : Long

    @Update
    suspend fun updateNotesDAO(notesEntity : NotesEntity) :Int

    @Delete
    suspend fun deleteNotesDAO(notesEntity : NotesEntity):Int

    @Query("DELETE FROM NOTES")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM notes")
    fun getAllNotes() : LiveData<List<NotesEntity>>

}
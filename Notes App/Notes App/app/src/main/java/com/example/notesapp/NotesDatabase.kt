package com.example.notesapp

import android.content.Context
import androidx.room.*

@Database(entities = [NotesEntity::class],version = 2)
abstract class NotesDatabase : RoomDatabase() {

    abstract val notesDAO : NotesDAO

    companion object {
        @Volatile
        private var notesDatabaseInstance : NotesDatabase? = null
        fun getInstance(context: Context) : NotesDatabase {
            if (notesDatabaseInstance == null) {
                synchronized(this) {
                    notesDatabaseInstance = Room.databaseBuilder(
                                                    context,
                                                    NotesDatabase :: class.java,
                                                    "notesDatabase").build()
                }
            }
            return notesDatabaseInstance!!
        }


    }
}
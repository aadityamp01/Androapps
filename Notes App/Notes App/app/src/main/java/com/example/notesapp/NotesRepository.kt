package com.example.notesapp

class NotesRepository(private  val dao : NotesDAO) {

    var notes = dao.getAllNotes()

    suspend fun insert(notesEntity: NotesEntity) : Long {
        return dao.insertNotesDAO(notesEntity)
    }

    suspend fun update(notesEntity: NotesEntity) :Int {
        return dao.updateNotesDAO(notesEntity)
    }

    suspend fun delete(notesEntity: NotesEntity) :Int {
       return dao.deleteNotesDAO(notesEntity)
    }

    suspend fun  deleteAll(): Int {
        return dao.deleteAll()
    }
}
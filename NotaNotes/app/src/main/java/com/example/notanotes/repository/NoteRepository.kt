package com.example.notanotes.repository

import androidx.room.Query
import com.example.notanotes.database.noteDatabase
import com.example.notanotes.model.Note


class NoteRepository (private val db: noteDatabase){

    suspend fun insertNote(note: Note) = db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)

    fun getAllNote() =db.getNoteDao().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)


}

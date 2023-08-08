package com.satyam.notesapp

import android.app.Application
import androidx.lifecycle.LiveData
import com.satyam.notesapp.utils.subscribeOnBackground

class NoteRepository(application: Application) {
     private var noteDao: NoteDao
     private var allNotes: LiveData<List<Note>>

     private val database = NoteDatabase.getInstance(application)

     init {
         noteDao = database.noteDao()
         allNotes = noteDao.getAllNotes()
     }

     fun insert(note: Note) {
         subscribeOnBackground {
             noteDao.insert(note)
         }
     }


     fun delete(note: Note) {
         subscribeOnBackground {
             noteDao.delete(note)
         }
     }


     fun getAllNotes(): LiveData<List<Note>> {
         return allNotes
     }
}
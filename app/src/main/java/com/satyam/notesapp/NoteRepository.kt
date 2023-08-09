package com.satyam.notesapp

import android.app.Application
import androidx.lifecycle.LiveData
import com.satyam.notesapp.utils.doInBackground

class NoteRepository(application: Application) {
     private var noteDao: NoteDao
     private var allNotes: LiveData<List<Note>>

     private val database = NoteDatabase.getInstance(application)

     init {
         noteDao = database.noteDao()
         allNotes = noteDao.getAllNotes()
     }

     fun insert(note: Note) {
         doInBackground {
             noteDao.insert(note)
         }
     }


     fun delete(note: Note) {
         doInBackground {
             noteDao.delete(note)
         }
     }

    fun update(note:Note){
        doInBackground {
            noteDao.update(note)
        }
    }

     fun getAllNotes(): LiveData<List<Note>> {
         return allNotes
     }
}
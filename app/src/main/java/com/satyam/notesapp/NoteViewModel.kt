package com.satyam.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.satyam.notesapp.utils.subscribeOnBackground
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) :AndroidViewModel(application) {
    private val repository = NoteRepository(application)
    private val allNotes = repository.getAllNotes()

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun delete(note: Note) {
        repository.delete(note)
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }


}
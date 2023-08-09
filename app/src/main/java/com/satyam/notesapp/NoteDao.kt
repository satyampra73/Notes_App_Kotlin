package com.satyam.notesapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Update
    fun update(note:Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>


}
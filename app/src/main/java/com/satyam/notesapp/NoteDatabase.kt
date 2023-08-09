package com.satyam.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.satyam.notesapp.utils.doInBackground

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase :RoomDatabase(){

    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: NoteDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): NoteDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, NoteDatabase::class.java,
                    "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(instance!!)
            }
        }

        private fun populateDatabase(db: NoteDatabase) {
            val noteDao = db.noteDao()
            doInBackground {
                noteDao.insert(Note("title 1", 1))
                noteDao.insert(Note("title 2", 2))
                noteDao.insert(Note("title 3", 3))

            }
        }
    }

}
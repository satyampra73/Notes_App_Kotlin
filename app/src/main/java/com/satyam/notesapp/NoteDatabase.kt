package com.satyam.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.satyam.notesapp.utils.subscribeOnBackground

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase :RoomDatabase(){
//    abstract fun getNoteDao(): NoteDao
//
//    companion object {
//        // Singleton prevents multiple instances of database opening at the
//        // same time.
//        @Volatile
//        private var INSTANCE: NoteDatabase? = null
//
//        fun getDatabase(context: Context): NoteDatabase {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    NoteDatabase::class.java,
//                    "note_database"
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//    }

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
            subscribeOnBackground {
                noteDao.insert(Note("title 1", 1))
                noteDao.insert(Note("title 2", 2))
                noteDao.insert(Note("title 3", 3))

            }
        }
    }

}
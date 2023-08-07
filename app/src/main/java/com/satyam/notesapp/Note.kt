package com.satyam.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.nio.channels.InterruptedByTimeoutException
@Entity(tableName = "notes_table")
data class Note (@ColumnInfo(name = "text")
                 val text:String,
                 @PrimaryKey(autoGenerate = true)
                 var id: Int =0)

//@Entity(tableName = "notes")
//data class Note(
//    @PrimaryKey
//    @ColumnInfo(name = "dateAdded")
//    val dateAdded: Date,
//    @ColumnInfo(name = "noteText")
//    val noteText: String
//)
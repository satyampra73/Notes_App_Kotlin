package com.satyam.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.nio.channels.InterruptedByTimeoutException
@Entity(tableName = "notes_table")
data class Note (@ColumnInfo(name = "text")
                 var text:String,
                 @PrimaryKey(autoGenerate = false)
                 val id: Int? = null)

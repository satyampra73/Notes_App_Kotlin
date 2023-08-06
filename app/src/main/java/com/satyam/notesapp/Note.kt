package com.satyam.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.nio.channels.InterruptedByTimeoutException
@Entity(tableName = "notes_table")
class Note (@ColumnInfo(name = "text")val text:String){
    @PrimaryKey(autoGenerate = true) var id=0
}
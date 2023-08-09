package com.satyam.notesapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRvAdapter {
    lateinit var viewModel: NoteViewModel
    lateinit var etInput: EditText
    lateinit var btnSubmit: AppCompatButton
    lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etInput = findViewById(R.id.etInput)
        btnSubmit = findViewById(R.id.btnSubmit)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRvAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        viewModel.getAllNotes().observe(this, Observer { list ->
            list?.let {
                adapter.delete(it)
            }
        })


    }

    override fun onDeleteClicked(note: Note) {
        viewModel.delete(note)
    }

    override fun onEditClicked(note: Note) {
        viewModel.update(note)
    }

    fun submitData(view: View) {
        val noteText=etInput.text.toString()
        if (noteText.isNotEmpty()){
            viewModel.insert(Note(noteText))
            Toast.makeText(this,"$noteText Inserted",Toast.LENGTH_SHORT).show()
        }
    }
}
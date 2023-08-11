package com.satyam.notesapp.LiveDataTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.satyam.notesapp.R

class ActivityOne : AppCompatActivity() {
    private lateinit var oneViewModel: OneViewModel
    private lateinit var btn:Button
    private lateinit var txtView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        btn=findViewById(R.id.btn)
        txtView=findViewById(R.id.txtView)
        oneViewModel=ViewModelProvider(this).get(OneViewModel::class.java)

        oneViewModel.factsLiveData.observe(this, {
            txtView.text=it
        })

        btn.setOnClickListener{
            oneViewModel.updateLiveData()
        }

    }
}
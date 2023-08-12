package com.satyam.notesapp.liveDataTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.satyam.notesapp.R
import com.satyam.notesapp.databinding.ActivityOneBinding

class ActivityOne : AppCompatActivity() {
    private lateinit var oneViewModel: OneViewModel
    private lateinit var btn: Button
    lateinit var binding: ActivityOneBinding
    private lateinit var txtView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_one)
        btn = findViewById(R.id.btn)
        txtView = findViewById(R.id.txtView)
        oneViewModel = ViewModelProvider(this).get(OneViewModel::class.java)

//        oneViewModel.factsLiveData.observe(this, {
//            txtView.text = it
//        })
        binding.myViewModel = oneViewModel
        binding.lifecycleOwner = this

    }
}
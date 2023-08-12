package com.satyam.notesapp.dataBindingTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.satyam.notesapp.R
import com.satyam.notesapp.databinding.ActivityTwoBinding

class ActivityTwo : AppCompatActivity() {

    lateinit var binding: ActivityTwoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_two)

        val data = MyModel("Satya", "Dev")
        binding.quote = data
    }
}
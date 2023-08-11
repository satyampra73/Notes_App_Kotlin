package com.satyam.notesapp.LiveDataTest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OneViewModel : ViewModel() {
    private val factsLiveDataObject = MutableLiveData<String>("this is fact")

    val factsLiveData: LiveData<String>
        get() = factsLiveDataObject


    fun updateLiveData() {
        factsLiveDataObject.value = "This is changed"
    }
}
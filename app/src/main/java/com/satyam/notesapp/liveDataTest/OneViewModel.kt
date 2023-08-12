package com.satyam.notesapp.liveDataTest

import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OneViewModel : ViewModel() {

    //for One way data binding we can go with Live data but if
    // we want to use two way data binding then we should go with MutableLiveData
    val factsLiveData = MutableLiveData<String>("this is fact")

//    val factsLiveData: LiveData<String>
//        get() = factsLiveDataObject


    fun updateLiveData() {
        factsLiveData.value = "This is changed"
    }
}
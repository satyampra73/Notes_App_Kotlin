package com.satyam.notesapp.utils

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun doInBackground(function: () -> Unit) {
    Single.fromCallable {
        function()
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
}
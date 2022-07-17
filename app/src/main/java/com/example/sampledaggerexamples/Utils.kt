package com.example.sampledaggerexamples

import android.view.View

import com.google.android.material.snackbar.Snackbar



    fun View.visible(isVisible : Boolean?){
        visibility = if(isVisible!!) View.VISIBLE else View.GONE
    }

    fun View.snackbar(message:String?){
        val snackabr = message?.let { Snackbar.make(this, it,Snackbar.LENGTH_LONG).show() }
    }

    fun View.IconVisibilitVisible(isVisible : Boolean?){
    visibility = if(isVisible!!) View.GONE else View.VISIBLE
}


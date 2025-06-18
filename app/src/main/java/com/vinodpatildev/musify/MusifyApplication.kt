package com.vinodpatildev.musify

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MusifyApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        // Rest of your code
    }
}
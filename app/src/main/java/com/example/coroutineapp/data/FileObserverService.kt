package com.example.coroutineapp.data

import android.app.Service
import android.content.Intent
import android.os.IBinder
import javax.inject.Inject

class FileObserverService @Inject constructor(): Service() {



    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}
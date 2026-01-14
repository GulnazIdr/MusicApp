package com.example.coroutineapp.data

import android.database.ContentObserver
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.coroutineapp.domain.DirectoryChangeUseCase
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.Q)
class AudioObserver @Inject constructor(
    private val directoryChangeUseCase: DirectoryChangeUseCase
) : ContentObserver(Handler(Looper.getMainLooper())) {

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        directoryChangeUseCase.directoryUpdated()
    }
}
package com.example.coroutineapp.domain

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class DownloadCompletedReciever: BroadcastReceiver() {
    private lateinit var downloadManager: DownloadManager

    override fun onReceive(context: Context?, intent: Intent?) {
        downloadManager = context?.getSystemService(DownloadManager::class.java)!!

        if(intent?.action == "android.intent.action.DOWNLOAD_COMPLETE") {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
            if (id != -1L) {
                val query = DownloadManager.Query().setFilterById(id)
                val cursor = downloadManager.query(query)

                if (cursor.moveToFirst()) {
                    val statusIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                    val status = cursor.getInt(statusIndex)

                    when (status) {
                        DownloadManager.STATUS_SUCCESSFUL -> {
                            val uriIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)
                            val localUri = cursor.getString(uriIndex)
                            println("Download successful! File saved at: $localUri")
                            Toast.makeText(
                                context,
                                "Download completed successfully!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        DownloadManager.STATUS_FAILED -> {
                            val reasonIndex = cursor.getColumnIndex(DownloadManager.COLUMN_REASON)
                            val reason = cursor.getInt(reasonIndex)
                            Log.d("Download failed! Reason code ", "$reason")
                            Toast.makeText(
                                context,
                                "Download failed! Check logs.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                cursor.close()
            }
        }
    }
}
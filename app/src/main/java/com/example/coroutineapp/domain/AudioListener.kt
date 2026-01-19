package com.example.coroutineapp.domain

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.MediaController
import androidx.core.net.toUri
import java.io.IOException
import javax.inject.Inject

class AudioListener @Inject constructor(
    private val context: Context
): MediaController.MediaPlayerControl {

    var musicId: Long = 0

    private val query: Uri = if(Build.VERSION.SDK_INT >= 29)
        MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
    else
        MediaStore.Audio.Media.getContentUri("external")

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    override fun getCurrentPosition(): Int = mediaPlayer.currentPosition

    override fun getDuration(): Int = mediaPlayer.duration

    override fun isPlaying(): Boolean =  mediaPlayer.isPlaying

    override fun pause() {
        mediaPlayer.pause()
    }

    override fun seekTo(p0: Int) {
        mediaPlayer.seekTo(p0)
    }

    override fun start() {
        //mediaPlayer.pre
        try {
            Log.d("music_id", musicId.toString())
            mediaPlayer.setDataSource(context, "$query/$musicId".toUri())
            mediaPlayer.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
                override fun onPrepared(mp: MediaPlayer) {
                    mp.start()
                }
            })
            mediaPlayer.prepareAsync()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun startPrepared(){
        mediaPlayer.start()
    }

    fun stopAudio(){
        mediaPlayer.stop()
    }

    fun reset() = mediaPlayer.reset()

    fun setVolume(left: Float, right: Float){
        mediaPlayer.setVolume(left, right)
    }

    override fun canPause(): Boolean {
        TODO("Not yet implemented")
    }

    override fun canSeekBackward(): Boolean {
        TODO("Not yet implemented")
    }

    override fun canSeekForward(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAudioSessionId(): Int {
        TODO("Not yet implemented")
    }

    override fun getBufferPercentage(): Int {
        TODO("Not yet implemented")
    }
}
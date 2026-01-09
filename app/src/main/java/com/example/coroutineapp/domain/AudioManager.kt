package com.example.coroutineapp.domain

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.MediaController
import androidx.annotation.RequiresApi
import javax.inject.Inject

class AudioManager @Inject constructor(
    context: Context
): MediaController.MediaPlayerControl {

    val query: Uri =  if(Build.VERSION.SDK_INT >= 29)
        MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
    else
        MediaStore.Audio.Media.getContentUri("external")

    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, query)

    override fun getCurrentPosition() = mediaPlayer.currentPosition

    override fun getDuration() = mediaPlayer.duration

    override fun isPlaying() = mediaPlayer.isPlaying

    override fun pause() {
        Log.d("click happened4", "")

        mediaPlayer.pause()
        pauseAudio()
        Log.d("click happened5", "")
    }

    override fun seekTo(p0: Int) {
        mediaPlayer.seekTo(p0)
    }

    override fun start() {
        mediaPlayer.start()
    }

    fun provideMedia() = mediaPlayer

    private val audioFocusListener = object : AudioManager.OnAudioFocusChangeListener{
        override fun onAudioFocusChange(focusChange: Int) {
            when (focusChange) {
                AudioManager.AUDIOFOCUS_GAIN -> {
                    start()
                }
                AudioManager.AUDIOFOCUS_LOSS -> {
                    stopAudio()
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                    pause()
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                    mediaPlayer.setVolume(0.2f, 0.2f)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val focusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN).run {
        setAudioAttributes(AudioAttributes.Builder().run {
            setUsage(AudioAttributes.USAGE_GAME)
            setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            build()
        })
        setAcceptsDelayedFocusGain(true)
        setOnAudioFocusChangeListener(audioFocusListener)
        build()
    }

    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    @RequiresApi(Build.VERSION_CODES.O)
    fun requestFocus(){
        audioManager.requestAudioFocus(focusRequest)
    }

    private fun pauseAudio(){
        audioManager.abandonAudioFocus(audioFocusListener)
    }

    private fun stopAudio(){
        mediaPlayer.stop()
        audioManager.abandonAudioFocus(audioFocusListener)
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
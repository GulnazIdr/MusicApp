package com.example.coroutineapp.domain

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import javax.inject.Inject

class AudioManager @Inject constructor(
    context: Context,
    private val audioListener: AudioListener
) {
    private var tempMusicId: Long = 0
    private var isPrepared = false

    fun getCurrentPosition() = audioListener.currentPosition

    fun isPlaying(): Boolean = audioListener.isPlaying

    fun seekTo(p0: Int) {
        audioListener.seekTo(p0)
    }

    fun start(musicId: Long) {
        tempMusicId =
            if(tempMusicId != musicId){
                isPrepared = false
                musicId
            }
            else{
                isPrepared = true
                tempMusicId
            }

        if (isPrepared) audioListener.startPrepared()
        else {
            audioListener.reset()
            audioListener.musicId = musicId
            audioListener.start()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun requestFocus() {
        val res = audioManager.requestAudioFocus(focusRequest)
        when (res) {
            AudioManager.AUDIOFOCUS_REQUEST_GRANTED -> {
                Log.d("requested", "true")
            }
            else ->  Log.d("requested", "false")
        }
    }

    fun pauseAudio(){
        audioListener.pause()
        //audioManager.abandonAudioFocus(audioFocusListener)
    }

    fun stopAudio(){
        audioListener.stopAudio()
        audioManager.abandonAudioFocus(audioFocusListener)
    }

    private val audioFocusListener = object : AudioManager.OnAudioFocusChangeListener{
        override fun onAudioFocusChange(focusChange: Int) {
            when (focusChange) {
                AudioManager.AUDIOFOCUS_GAIN -> {
                    audioListener.start()
                }
                AudioManager.AUDIOFOCUS_LOSS -> {
                    stopAudio()
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                    pauseAudio()
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                    audioListener.setVolume(0.2f, 0.2f)
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

    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
}
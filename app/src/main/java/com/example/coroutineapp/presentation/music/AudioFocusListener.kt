package com.example.coroutineapp.presentation.music

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import javax.inject.Inject

class AudioFocusListener @Inject constructor(
    context: Context,
){
    private val _isPlaying = mutableStateOf(false)
    val isPlaying = _isPlaying

    val query: Uri =  if(Build.VERSION.SDK_INT >= 29)
        MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
    else
        MediaStore.Audio.Media.getContentUri("external")

    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, query)
    private val audioFocusListener = object : AudioManager.OnAudioFocusChangeListener{
        override fun onAudioFocusChange(focusChange: Int) {
            when (focusChange) {
                AudioManager.AUDIOFOCUS_GAIN -> {
                    mediaPlayer.start()
                    _isPlaying.value = true
                }
                AudioManager.AUDIOFOCUS_LOSS -> {
                    mediaPlayer.stop()
                    _isPlaying.value = false
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                    mediaPlayer.pause()
                    _isPlaying.value = false
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                    mediaPlayer.setVolume(0.2f, 0.2f)
                    _isPlaying.value = true
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
        val res = audioManager.requestAudioFocus(focusRequest)

        val focusLock = Any()
        synchronized(focusLock) {
            when (res) {
                AudioManager.AUDIOFOCUS_REQUEST_FAILED ->{
                    _isPlaying.value = false
                }
                AudioManager.AUDIOFOCUS_REQUEST_GRANTED -> {
                    mediaPlayer.start()
                    _isPlaying.value = true
                }
                AudioManager.AUDIOFOCUS_REQUEST_DELAYED -> {
                    _isPlaying.value = false
                }
                else -> {
                    _isPlaying.value = false
                }
            }
        }
    }

    fun pauseAudio(){
        mediaPlayer.pause()
        _isPlaying.value = false
        audioManager.abandonAudioFocus(audioFocusListener)
    }

    fun stopAudio(){
        mediaPlayer.stop()
        _isPlaying.value = false
        audioManager.abandonAudioFocus(audioFocusListener)
    }
}
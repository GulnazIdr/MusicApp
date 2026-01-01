package com.example.coroutineapp.data

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.example.coroutineapp.data.models.MusicDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class AudioReader @Inject constructor(
    private val context: Context
) {
    // TODO: understand why without suupervisor it doesnt have launch function
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    val audioFiles = mutableListOf<MusicDto>()

    fun getFiles(): List<MusicDto>{
        if (audioFiles.isNotEmpty()) return audioFiles

        val skipQuery =
            if(Build.VERSION.SDK_INT <= 32)
                ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            else false

        if(skipQuery) return emptyList()

        val queryUri =
            if(Build.VERSION.SDK_INT >= 29)
            // TODO: understand the difference
           //     MediaStore.Downloads. .getContentUri(MediaStore.VOLUME_EXTERNAL)
                MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            else
              //  MediaStore.Downloads.getContentUri("external")
                MediaStore.Audio.Media.getContentUri("external")

        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.ARTIST,
            MediaStore.Files.FileColumns.DURATION
        ) //projection[0] - _id, projection[1] - _display_name

        context.contentResolver.query(
            queryUri, projection, null, null, null
        )?.use { cursor ->
            // TODO: or throw what
            val idCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
            val nameCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
            val artistCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.ARTIST)
            val durationColor = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DURATION)

            while (cursor.moveToNext()){
                val id = cursor.getLong(idCol)
                val nameField = cursor.getString(nameCol)
                val artist = cursor.getString(artistCol)
                val duration = cursor.getString(durationColor)
                val uri = ContentUris.withAppendedId(queryUri, id) //file's path queryUri + id

                if(nameField != null){
                    val name =
                        nameField.substringAfter('-').replaceFirst(" ", "").replaceFirst("_","").replace('_', ' ').replaceAfterLast('_', "")
                            .replaceAfterLast(' ', "")
                    audioFiles.add(MusicDto(id, name, artist, duration, null, uri))
                }
            }
        }

        return audioFiles
    }

    fun loadBitmapIfNeeded(context: Context, index: Int) {
        if (audioFiles[index].cover != null) return

        scope.launch{
            val bitmap = getAlbumArt(context, audioFiles[index].contentUri)
//            if(bitmap != null)
//                audioFiles[index].apply {
//                     this.cover = bitmap
//                }
            audioFiles[index] = audioFiles[index].copy(cover = bitmap)
        }
    }

    private fun getAlbumArt(context: Context, uri: Uri): Bitmap?{
        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(context, uri)
        val data = mmr.embeddedPicture
        // TODO: understand the resource thing and arguments
        return if(data != null){
            BitmapFactory.decodeByteArray(data, 0, data.size)
        }else
            null
           // BitmapFactory.decodeResource(context.resources, R.drawable.default_audio)
    }
}
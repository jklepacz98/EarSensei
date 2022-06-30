package com.example.earsensei

import android.content.Context
import android.media.MediaPlayer
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotesPlayer(val context: Context, val notes: List<Note>) {

    var job: Job = Job()
    var mediaPlayers: MutableList<MediaPlayer> = mutableListOf()

    init {
        notes.forEach() {
            var mediaPlayer: MediaPlayer = MediaPlayer.create(context, it.audioResource)
            mediaPlayers.add(mediaPlayer)
        }
    }

    suspend fun playSingleNote() {
        mediaPlayers.forEach() {
            if (it.isPlaying) {
                it.stop()
                it.prepare()
            }
            it.start()
            delay(850)
        }

    }

    fun playMultipleNotes() {
        job.cancel()
        job = MainScope().launch {
            playSingleNote()
        }
    }
}
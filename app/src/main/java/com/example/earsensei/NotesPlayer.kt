package com.example.earsensei

import android.content.Context
import android.media.MediaPlayer
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotesPlayer(val context: Context) {

    var job: Job = Job()
    private val mediaPlayers: MutableList<MediaPlayer> = mutableListOf()

    private suspend fun playSingleNote() {
        mediaPlayers.forEach() {
            if (it.isPlaying) {
                it.stop()
                it.prepare()
            }
            it.start()
            delay(DELAY)
        }
    }

    fun playMultipleNotes() {
        job.cancel()
        job = MainScope().launch {
            playSingleNote()
        }
    }

    fun setNotes(noteIndices: List<Int>) {
        val notes = noteIndices.map { Note.NOTES[it] }
        mediaPlayers.forEach {
            it.release()
        }
        mediaPlayers.clear()
        notes.forEach() {
            val mediaPlayer: MediaPlayer = MediaPlayer.create(context, it!!.audioResource)
            mediaPlayers.add(mediaPlayer)
        }
    }

    private companion object {
        const val DELAY = 850L
    }
}
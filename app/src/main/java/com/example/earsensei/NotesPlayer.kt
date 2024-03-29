package com.example.earsensei

import android.content.Context
import android.media.MediaPlayer
import kotlinx.coroutines.*

class NotesPlayer(val context: Context) {

    var job: Job = Job()
    private val mediaPlayers: MutableList<MediaPlayer> = mutableListOf()

    private suspend fun playSingleNote(mediaPlayer: MediaPlayer) {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }
        mediaPlayer.start()
        delay(DELAY)
    }

    fun playMultipleNotes(scope: CoroutineScope) {
        job.cancel()
        job = scope.launch(Dispatchers.IO) {
            mediaPlayers.forEach { playSingleNote(it) }
        }
    }

    fun setNotes(noteIndices: List<Int>) {
        clear()
        val notes = noteIndices.map { Note.NOTES[it] }
        notes.forEach() {
            val mediaPlayer: MediaPlayer = MediaPlayer.create(context, it!!.audioResource)
            mediaPlayers.add(mediaPlayer)
        }
    }

    fun clear() {
        job.cancel()
        mediaPlayers.forEach { it.release() }
        mediaPlayers.clear()
    }

    private companion object {
        const val DELAY = 850L
    }
}
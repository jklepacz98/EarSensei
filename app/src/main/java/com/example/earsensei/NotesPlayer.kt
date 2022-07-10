package com.example.earsensei

import android.content.Context
import android.media.MediaPlayer
import kotlinx.coroutines.*

class NotesPlayer(val context: Context) {

    var job: Job = Job()
    val mediaPlayers: MutableList<MediaPlayer> = mutableListOf()


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

    fun setNotes(notes: List<Note>){
        job.cancel()
        mediaPlayers.clear()
        notes.forEach(){
            val mediaPlayer: MediaPlayer = MediaPlayer.create(context, it.audioResource)
            mediaPlayers.add(mediaPlayer)
        }
    }
}
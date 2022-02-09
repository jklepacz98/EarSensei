package com.example.earsensei

import android.content.Context
import kotlinx.coroutines.delay

class NotesPlayer(val notes: Array<Note>, val context: Context){
    suspend fun play(){
        for (note in notes) {
            note.play(context)
            delay(850)
        }
    }
}

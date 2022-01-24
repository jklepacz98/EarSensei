package com.example.earsensei

import android.content.Context
import kotlinx.coroutines.delay

class NotesPlayer(var notes : List<Note>, var delay : Long) {
    suspend fun playNotes(context : Context){
        notes.forEach({
            it.play(context)
            delay(delay)
        })
    }
}
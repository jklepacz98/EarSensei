package com.example.earsensei

import android.content.Context
import kotlinx.coroutines.delay

class MultipleNotesPlayer(val notes: Array<NotePlayer>, val context: Context){
    suspend fun play(){
        for (note in notes) {
            note.play(context)
            delay(850)
        }
    }
}

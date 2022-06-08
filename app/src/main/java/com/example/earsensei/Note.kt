package com.example.earsensei

import android.content.ContentValues.TAG
import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class Note(val number: Int, val name: String, val audioResource: Int) {
    companion object {
        val OCTAVE_SIZE = 12;


        val notePlayers = arrayOf(
            Note(0, "C3", R.raw.c3),
            Note(1, "Cis3", R.raw.cis3),
            Note(2, "D3", R.raw.d3),
            Note(3, "Dis3", R.raw.dis3),
            Note(4, "E3", R.raw.e3),
            Note(5, "F3", R.raw.f3),
            Note(6, "Fis3", R.raw.fis3),
            Note(7, "G3", R.raw.g3),
            Note(8, "Gis3", R.raw.gis3),
            Note(9, "A3", R.raw.a3),
            Note(10, "Ais3", R.raw.ais3),
            Note(11, "B3", R.raw.b3),

            Note(12, "C4", R.raw.c4),
            Note(13, "Cis4", R.raw.cis4),
            Note(14, "D4", R.raw.d4),
            Note(15, "Dis4", R.raw.dis4),
            Note(16, "E4", R.raw.e4),
            Note(17, "F4", R.raw.f4),
            Note(18, "Fis4", R.raw.fis4),
            Note(19, "G4", R.raw.g4),
            Note(20, "Gis4", R.raw.gis4),
            Note(21, "A4", R.raw.a4),
            Note(22, "Ais4", R.raw.ais4),
            Note(23, "B4", R.raw.b4),

            Note(24, "C5", R.raw.c5),
            Note(25, "Cis5", R.raw.cis5),
            Note(26, "D5", R.raw.d5),
            Note(27, "Dis5", R.raw.dis5),
            Note(28, "E5", R.raw.e5),
            Note(29, "F5", R.raw.f5),
            Note(30, "Fis5", R.raw.fis5),
            Note(31, "G5", R.raw.g5),
            Note(32, "Gis5", R.raw.gis5),
            Note(33, "A5", R.raw.a5),
            Note(34, "Ais5", R.raw.ais5),
            Note(35, "B5", R.raw.b5),

            Note(36, "C6", R.raw.c6)
        )


        val scales = mapOf<String, Array<Int>>(
            "Major" to arrayOf(0, 2, 4, 5, 7, 9, 11, 12),
            "Natural Minor" to arrayOf(0, 2, 3, 5, 7, 8, 10, 12),
            "Harmonic Minor" to arrayOf(0, 2, 3, 5, 7, 8, 11, 12)
        )

        val chords = mapOf<String, Array<Int>>(
            "Major" to arrayOf(0, 4, 7),
            "Minor" to arrayOf(0, 3, 7),
            "Diminished" to arrayOf(0, 3, 6),
            "Augemented" to arrayOf(0, 4, 8),
            "Major 7th" to arrayOf(0, 4, 7, 11),
            "Dominant 7th" to arrayOf(0, 4, 7, 10),
            "Minor 7th" to arrayOf(0, 3, 7, 10),
        )


        val intervals = mapOf<String, Int>(
            "Perfect 1st" to 0,
            "Minor 2nd" to 1,
            "Major 2nd" to 2,
            "Minor 3rd" to 3,
            "Major 3rd" to 4,
            "Perfect 4th" to 5,
            "Tritone" to 6,
            "Perfect 5th" to 7,
            "Minor 6th" to 8,
            "Major 6th" to 9,
            "Minor 7th" to 10,
            "Major 7th" to 11,
            "Octave" to 12,
            "Minor 9th" to 13,
            "Major 9th" to 14
        )

        val notes = mapOf<String, Int>(
            "C" to 0,
            "Cis" to 1,
            "D" to 2,
            "Dis" to 3,
            "E" to 4,
            "F" to 5,
            "Fis" to 6,
            "G" to 7,
            "Gis" to 8,
            "A" to 9,
            "Ais" to 10,
            "B" to 11,
        )
    }
}

class NotesPlayer(val context: Context, val notes: List<Note>) {

    var job: Job = Job()
    var mediaPlayers: MutableList<MediaPlayer> = mutableListOf()

    init {
        notes.forEach() {
            var mediaPlayer: MediaPlayer = MediaPlayer.create(context, it.audioResource)
            mediaPlayers.add(mediaPlayer)
        }
    }

    suspend fun play() {
        mediaPlayers.forEach() {
            if (it.isPlaying) {
                it.stop()
                it.prepare()
            }
            it.start()
            delay(850)
        }

    }

    fun playNotes() {
        job.cancel()
        job = MainScope().launch {
            play()
        }
    }
}




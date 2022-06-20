package com.example.earsensei

import android.content.Context
import android.media.MediaPlayer
import com.example.earsensei.Note.Companion.notes
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class Note(val name: String, val number: Int, val audioResource: Int) {
    companion object {
        val OCTAVE_SIZE = 12

        val notePlayers = arrayOf(
            Note("C3", 0, R.raw.c3),
            Note("Cis3", 1, R.raw.cis3),
            Note("D3", 2, R.raw.d3),
            Note("Dis3", 3, R.raw.dis3),
            Note("E3", 4, R.raw.e3),
            Note("F3", 5, R.raw.f3),
            Note("Fis3", 6, R.raw.fis3),
            Note("G3", 7, R.raw.g3),
            Note("Gis3", 8, R.raw.gis3),
            Note("A3", 9, R.raw.a3),
            Note("Ais3", 10, R.raw.ais3),
            Note("B3", 11, R.raw.b3),

            Note("C4", 12, R.raw.c4),
            Note("Cis4", 13, R.raw.cis4),
            Note("D4", 14, R.raw.d4),
            Note("Dis4", 15, R.raw.dis4),
            Note("E4", 16, R.raw.e4),
            Note("F4", 17, R.raw.f4),
            Note("Fis4", 18, R.raw.fis4),
            Note("G4", 19, R.raw.g4),
            Note("Gis4", 20, R.raw.gis4),
            Note("A4", 21, R.raw.a4),
            Note("Ais4", 22, R.raw.ais4),
            Note("B4", 23, R.raw.b4),

            Note("C5", 24, R.raw.c5),
            Note("Cis5", 25, R.raw.cis5),
            Note("D5", 26, R.raw.d5),
            Note("Dis5", 27, R.raw.dis5),
            Note("E5", 28, R.raw.e5),
            Note("F5", 29, R.raw.f5),
            Note("Fis5", 30, R.raw.fis5),
            Note("G5", 31, R.raw.g5),
            Note("Gis5", 32, R.raw.gis5),
            Note("A5", 33, R.raw.a5),
            Note("Ais5", 34, R.raw.ais5),
            Note("B5", 35, R.raw.b5),

            Note("C6", 36, R.raw.c6)
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


        val intervals : LinkedHashMap<String, Int> = linkedMapOf<String, Int>(
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

         val notesWithOctave = mapOf<String, Int>(
             "C3" to 0,
             "Cis3" to 1,
             "D3" to 2,
             "Dis3" to 3,
             "E3" to 4,
             "F3" to 5,
             "Fis3" to 6,
             "G3" to 7,
             "Gis3" to 8,
             "A3" to 9,
             "Ais3" to 10,
             "B3" to 11,

             "C4" to 12,
             "Cis4" to 13,
             "D4" to 14,
             "Dis4" to 15,
             "E4" to 16,
             "F4" to 17,
             "Fis4" to 18,
             "G4" to 19,
             "Gis4" to 20,
             "A4" to 21,
             "Ais4" to 22,
             "B4" to 23,

             "C5" to 24,
             "Cis5" to 25,
             "D5" to 26,
             "Dis5" to 27,
             "E5" to 28,
             "F5" to 29,
             "Fis5" to 30,
             "G5" to 31,
             "Gis5" to 32,
             "A5" to 33,
             "Ais5" to 34,
             "B5" to 35,

             "C6" to 36
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




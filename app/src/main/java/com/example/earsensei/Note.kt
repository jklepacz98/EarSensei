package com.example.earsensei

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.Resources
import android.media.MediaPlayer
import android.provider.ContactsContract
import java.util.*

data class Note(val number : Int , val name : String, val audioResource : Int) {



    fun play(context : Context){
        var mediaPlayer : MediaPlayer = MediaPlayer.create(context , this.audioResource)
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
        }
        mediaPlayer.start()
    }


}

val OCTAVE_SIZE = 12;


//TODO check if the scales are right
val scales = mapOf<String, Array<Int>>(
    "Major" to arrayOf(0, 2, 4, 5, 7, 9, 11, 12),
    "Natural Minor" to arrayOf(0, 2, 3, 5, 7, 8, 10, 12),
    "Harmonic Minor" to arrayOf(0, 2, 3, 5, 7, 8, 11, 12)
)

val chord = mapOf<String, Array<Int>>(
    "Major" to arrayOf(0, 4, 7),
    "Minor" to arrayOf(0, 3, 7),
    "Diminished" to arrayOf(0, 3, 6),
    "Augemented" to arrayOf(0, 4, 8)

)

val notes = listOf(
        Note(0, "c3", R.raw.c3),
        Note(1, "cis3", R.raw.cis3),
        Note(2,"d3", R.raw.d3),
        Note(3, "dis3", R.raw.dis3),
        Note(4,"e3", R.raw.e3),
        Note(5, "f3", R.raw.f3),
        Note(6,"fis3", R.raw.fis3),
        Note(7, "g3", R.raw.g3),
        Note(8, "gis3", R.raw.gis3),
        Note(9, "a3", R.raw.a3),
        Note(10, "ais3", R.raw.ais3),
        Note(11,"b3", R.raw.b3),

        Note(12, "c4", R.raw.c4),
        Note(13, "cis4", R.raw.cis4),
        Note(14,"d4", R.raw.d4),
        Note(15, "dis4", R.raw.dis4),
        Note(16,"e4", R.raw.e4),
        Note(17,"f4", R.raw.f4),
        Note(18,"fis4", R.raw.fis4),
        Note(19, "g4", R.raw.g4),
        Note(20, "gis4", R.raw.gis4),
        Note(21, "a4", R.raw.a4),
        Note(22, "ais4", R.raw.ais4),
        Note(23,"b4", R.raw.b4),

        Note(24,"c5", R.raw.c5),
        Note(25, "cis5", R.raw.cis5),
        Note(26,"d5", R.raw.d5),
        Note(27, "dis5", R.raw.dis5),
        Note(28,"e5", R.raw.e5),
        Note(29,"f5", R.raw.f5),
        Note(30,"fis5", R.raw.fis5),
        Note(31, "g5", R.raw.g5),
        Note(32, "gis5", R.raw.gis5),
        Note(33, "a5", R.raw.a5),
        Note(34, "ais5", R.raw.ais5),
        Note(35,"b5", R.raw.b5),

        Note(36, "c6", R.raw.c6)
)

val intervals = mapOf<Int, String>(

        0 to "Perfect 1st",
        1 to "Minor 2nd",
        2 to "Major 2nd",
        3 to "Minor 3rd",
        4 to "Major 3rd",
        5 to "Perfect 4th",
        6 to "Tritone",
        7 to "Perfect 5th",
        8 to "Minor 6th",
        9 to "Major 6th",
        10 to "Minor 7th",
        11 to "Major 7th",
        12 to "Octave"
)

val notesWithoutOctaveNumber = mapOf<Int, String>(
    0 to "C",
    1 to "Cis",
    2 to "D",
    3 to "Dis",
    4 to "E",
    5 to "F",
    6 to "Fis",
    7 to "G",
    8 to "Gis",
    9 to "A",
    10 to "Ais",
    11 to "B",
)
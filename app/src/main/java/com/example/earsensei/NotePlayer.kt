package com.example.earsensei

import android.content.Context
import android.media.MediaPlayer

data class NotePlayer(val number : Int, val name : String, val audioResource : Int){
    fun play(context : Context){
        var mediaPlayer : MediaPlayer = MediaPlayer.create(context , this.audioResource)
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
        }
        mediaPlayer.start()
    }


}

val OCTAVE_SIZE = 12;


val notePlayers = arrayOf(
        NotePlayer(0, "C3", R.raw.c3),
        NotePlayer(1, "Cis3", R.raw.cis3),
        NotePlayer(2,"D3", R.raw.d3),
        NotePlayer(3, "Dis3", R.raw.dis3),
        NotePlayer(4,"E3", R.raw.e3),
        NotePlayer(5, "F3", R.raw.f3),
        NotePlayer(6,"Fis3", R.raw.fis3),
        NotePlayer(7, "G3", R.raw.g3),
        NotePlayer(8, "Gis3", R.raw.gis3),
        NotePlayer(9, "A3", R.raw.a3),
        NotePlayer(10, "Ais3", R.raw.ais3),
        NotePlayer(11,"B3", R.raw.b3),

        NotePlayer(12, "C4", R.raw.c4),
        NotePlayer(13, "Cis4", R.raw.cis4),
        NotePlayer(14,"D4", R.raw.d4),
        NotePlayer(15, "Dis4", R.raw.dis4),
        NotePlayer(16,"E4", R.raw.e4),
        NotePlayer(17,"F4", R.raw.f4),
        NotePlayer(18,"Fis4", R.raw.fis4),
        NotePlayer(19, "G4", R.raw.g4),
        NotePlayer(20, "Gis4", R.raw.gis4),
        NotePlayer(21, "A4", R.raw.a4),
        NotePlayer(22, "Ais4", R.raw.ais4),
        NotePlayer(23,"B4", R.raw.b4),

        NotePlayer(24,"C5", R.raw.c5),
        NotePlayer(25, "Cis5", R.raw.cis5),
        NotePlayer(26,"D5", R.raw.d5),
        NotePlayer(27, "Dis5", R.raw.dis5),
        NotePlayer(28,"E5", R.raw.e5),
        NotePlayer(29,"F5", R.raw.f5),
        NotePlayer(30,"Fis5", R.raw.fis5),
        NotePlayer(31, "G5", R.raw.g5),
        NotePlayer(32, "Gis5", R.raw.gis5),
        NotePlayer(33, "A5", R.raw.a5),
        NotePlayer(34, "Ais5", R.raw.ais5),
        NotePlayer(35,"B5", R.raw.b5),

        NotePlayer(36, "C6", R.raw.c6)
)


val scales = mapOf<String, Array<Int>>(
        "Major" to arrayOf(0, 2, 4, 5, 7, 9, 11, 12),
        "Natural Minor" to arrayOf(0, 2, 3, 5, 7, 8, 10, 12),
        "Harmonic Minor" to arrayOf(0, 2, 3, 5, 7, 8, 11, 12)
)

val chords = mapOf<String, Array<Int>>(
        "Major"             to arrayOf(0, 4, 7),
        "Minor"             to arrayOf(0, 3, 7),
        "Diminished"        to arrayOf(0, 3, 6),
        "Augemented"        to arrayOf(0, 4, 8),
        "Major 7th"         to arrayOf(0, 4, 7, 11),
        "Dominant 7th"      to arrayOf(0, 4, 7, 10),
        "Minor 7th"         to arrayOf(0, 3, 7, 10),
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


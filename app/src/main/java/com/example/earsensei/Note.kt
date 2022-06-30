package com.example.earsensei

class Note(val name: String, val number: Int, val audioResource: Int) {
    companion object{
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
    }
}
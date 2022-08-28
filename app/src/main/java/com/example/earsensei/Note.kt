package com.example.earsensei

class Note(val name: String, val number: Int, val audioResource: Int) {
    companion object {
        val NOTES = mapOf(
            0 to Note("C3", 0, R.raw.c3),
            1 to Note("Cis3", 1, R.raw.cis3),
            2 to Note("D3", 2, R.raw.d3),
            3 to Note("Dis3", 3, R.raw.dis3),
            4 to Note("E3", 4, R.raw.e3),
            5 to Note("F3", 5, R.raw.f3),
            6 to Note("Fis3", 6, R.raw.fis3),
            7 to Note("G3", 7, R.raw.g3),
            8 to Note("Gis3", 8, R.raw.gis3),
            9 to Note("A3", 9, R.raw.a3),
            10 to Note("Ais3", 10, R.raw.ais3),
            11 to Note("B3", 11, R.raw.b3),

            12 to Note("C4", 12, R.raw.c4),
            13 to Note("Cis4", 13, R.raw.cis4),
            14 to Note("D4", 14, R.raw.d4),
            15 to Note("Dis4", 15, R.raw.dis4),
            16 to Note("E4", 16, R.raw.e4),
            17 to Note("F4", 17, R.raw.f4),
            18 to Note("Fis4", 18, R.raw.fis4),
            19 to Note("G4", 19, R.raw.g4),
            20 to Note("Gis4", 20, R.raw.gis4),
            21 to Note("A4", 21, R.raw.a4),
            22 to Note("Ais4", 22, R.raw.ais4),
            23 to Note("B4", 23, R.raw.b4),

            24 to Note("C5", 24, R.raw.c5),
            25 to Note("Cis5", 25, R.raw.cis5),
            26 to Note("D5", 26, R.raw.d5),
            27 to Note("Dis5", 27, R.raw.dis5),
            28 to Note("E5", 28, R.raw.e5),
            29 to Note("F5", 29, R.raw.f5),
            30 to Note("Fis5", 30, R.raw.fis5),
            31 to Note("G5", 31, R.raw.g5),
            32 to Note("Gis5", 32, R.raw.gis5),
            33 to Note("A5", 33, R.raw.a5),
            34 to Note("Ais5", 34, R.raw.ais5),
            35 to Note("B5", 35, R.raw.b5),

            36 to Note("C6", 36, R.raw.c6)
        )
    }
}
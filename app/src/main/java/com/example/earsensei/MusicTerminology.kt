package com.example.earsensei

class MusicTerminology {
    object Intervals{
        const val PERFECT_1ST = "Perfect 1st"
        const val MINOR_2ND = "Minor 2nd"
        const val MAJOR_2ND ="Major 2nd"
        const val MINOR_3RD = "Minor 3rd"
        const val MAJOR_3RD = "Major 3rd"
        const val PERFECT_4TH = "Perfect 4th"
        const val TRITONE = "Tritone"
        const val PERFECT_5TH = "Perfect 5th"
        const val MINOR_6TH = "Minor 6th"
        const val MAJOR_6TH = "Major 6th"
        const val MINOR_7TH = "Minor 7th"
        const val MAJOR_7TH = "Major 7th"
        const val OCATVE = "Octave"
        const val MINOR_9TH ="Minor 9th"
        const val MAJOR_9TH ="Major 9th"
    }

    object Translations{
        val intervals : Map<String, Int> = mapOf(
            Intervals.PERFECT_1ST to R.string.perfect1st,
            Intervals.MINOR_2ND to R.string.minor2nd,
            Intervals.MAJOR_3RD to R.string.major2nd,
            Intervals.MINOR_3RD to R.string.minor3rd,
            Intervals.MAJOR_3RD to R.string.major3rd,
            Intervals.PERFECT_4TH to R.string.perfect4th,
            Intervals.TRITONE to R.string.tritone,
            Intervals.PERFECT_5TH to R.string.perfect5th,
            Intervals.MINOR_6TH to R.string.minor6th,
            Intervals.MAJOR_6TH to R.string.major6th,
            Intervals.MINOR_7TH to R.string.minor7th,
            Intervals.MAJOR_7TH to R.string.major7th,
            Intervals.OCATVE to R.string.octave,
            Intervals.MINOR_9TH to R.string.minor9th,
            Intervals.MAJOR_9TH to R.string.major9th
        )
    }

    companion object {
        //todo
        val MAJOR = "Major"
        val MINOR = "Minor"

        val OCTAVE_SIZE = 12

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
            Intervals.PERFECT_1ST to 0,
            Intervals.MINOR_2ND to 1,
            Intervals.MAJOR_3RD to 2,
            Intervals.MINOR_3RD to 3,
            Intervals.MAJOR_3RD to 4,
            Intervals.PERFECT_4TH to 5,
            Intervals.TRITONE to 6,
            Intervals.PERFECT_5TH to 7,
            Intervals.MINOR_6TH to 8,
            Intervals.MAJOR_6TH to 9,
            Intervals.MINOR_7TH to 10,
            Intervals.MAJOR_7TH to 11,
            Intervals.OCATVE to 12,
            Intervals.MINOR_9TH to 13,
            Intervals.MAJOR_9TH to 14
        )

//        val intervals : LinkedHashMap<Int, String> = linkedMapOf<Int, String>(
//            0 to Intervals.PERFECT_1ST,
//            1 to Intervals.MINOR_2ND,
//            2 to Intervals.MAJOR_3RD,
//            3 to Intervals.MINOR_3RD,
//            4 to Intervals.MAJOR_3RD,
//            5 to Intervals.PERFECT_4TH,
//            6 to Intervals.TRITONE,
//            7 to Intervals.PERFECT_5TH,
//            8 to Intervals.MINOR_6TH,
//            9 to Intervals.MAJOR_6TH,
//            10 to Intervals.MINOR_7TH,
//            11 to Intervals.MAJOR_7TH,
//            12 to Intervals.OCATVE,
//            13 to Intervals.MINOR_9TH,
//            14 to Intervals.MAJOR_9TH
//        )

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






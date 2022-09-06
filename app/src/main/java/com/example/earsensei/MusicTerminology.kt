package com.example.earsensei

class MusicTerminology() {
    companion object {
        const val MAJOR = "Major"
        const val MINOR = "Minor"
        const val OCTAVE_SIZE = 12
    }
}

interface Musicable {
    fun getType(): String
}

enum class INTERVALS(val halfSteps: Int, val translation: Int) : Musicable {
    PERFECT_1ST(0, R.string.interval_perfect1st),
    MINOR_2ND(1, R.string.interval_minor2nd),
    MAJOR_2RD(2, R.string.interval_major2nd),
    MINOR_3RD(3, R.string.interval_minor3rd),
    MAJOR_3RD(4, R.string.interval_major3rd),
    PERFECT_4TH(5, R.string.interval_perfect4th),
    TRITONE(6, R.string.interval_tritone),
    PERFECT_5TH(7, R.string.interval_perfect5th),
    MINOR_6TH(8, R.string.interval_minor6th),
    MAJOR_6TH(9, R.string.interval_major6th),
    MINOR_7TH(10, R.string.interval_minor7th),
    MAJOR_7TH(11, R.string.interval_major7th),
    OCATVE(12, R.string.interval_octave),
    MINOR_9TH(13, R.string.interval_minor9th),
    MAJOR_9TH(14, R.string.interval_major9th);

    override fun getType(): String {
        return INTERVALS.getType()
    }

    companion object : Musicable {
        const private val type: String = "Intervals"
        override fun getType(): String {
            return type
        }
    }

}

enum class SCALES(val halfSteps: List<Int>, val translation: Int) : Musicable {
    MAJOR(listOf(0, 2, 4, 5, 7, 9, 11, 12), R.string.scale_major),
    NATURAL_MINOR(listOf(0, 2, 3, 5, 7, 8, 10, 12), R.string.scale_natural_minor),
    HARMONIC_MINOR(listOf(0, 2, 3, 5, 7, 8, 11, 12), R.string.scale_harmonic_minor);

    override fun getType(): String {
        return SCALES.getType()
    }

    companion object : Musicable {
        const private val type: String = "Scales"
        override fun getType(): String {
            return type
        }
    }
}


enum class CHORDS(val halfSteps: List<Int>, val translation: Int) : Musicable {
    MAJOR(listOf(0, 4, 7), R.string.chord_major),
    MINOR(listOf(0, 3, 7), R.string.chord_minor),
    DIMINISHED(listOf(0, 3, 6), R.string.chord_diminished),
    AUGMENTED(listOf(0, 4, 8), R.string.chord_augmented),
    MAJOR_7TH(listOf(0, 4, 7, 11), R.string.chord_major7th),
    DOMINANT_7TH(listOf(0, 4, 7, 10), R.string.chord_dominant7th),
    MINOR_7TH(listOf(0, 3, 7, 10), R.string.chord_minor7th);

    override fun getType(): String {
        return CHORDS.getType()
    }

    companion object : Musicable {
        const private val type: String = "Chords"
        override fun getType(): String {
            return type
        }
    }
}

val NOTES = mapOf<String, Int>(
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

val NOTES_WITH_OCTAVE = mapOf<String, Int>(
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









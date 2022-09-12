package com.example.earsensei


interface MusicTerminology {
    val type: String
    val musicMap: Map<String, MusicSomething>
}

interface MusicSomething {
    val name: String
    val order: Int
    val translation: Int
    fun getRange(): Map<String, Int>
    fun toNoteIndices(baseNote: Int): List<Int>
}

data class Interval(
    override val name: String,
    override val order: Int,
    val halfSteps: Int,
    override val translation: Int
) : MusicSomething {
    override fun getRange() =
        NOTES_WITH_OCTAVE.filter { it.value < NOTES_WITH_OCTAVE.size - halfSteps }

    val type = TYPE

    companion object {
        val TYPE = "Intervals"
    }

    override fun toNoteIndices(baseNote: Int): List<Int> {
        val secondNote = baseNote + halfSteps
        return listOf(baseNote, secondNote)
    }
}

object Intervals : MusicTerminology {

    override val type: String = Interval.TYPE

    val list = listOf(
        Interval("MINOR_2ND", 1, 1, R.string.interval_minor2nd),
        Interval("MAJOR_2RD", 2, 2, R.string.interval_major2nd),
        Interval("MINOR_3RD", 3, 3, R.string.interval_minor3rd),
        Interval("MAJOR_3RD", 4, 4, R.string.interval_major3rd),
        Interval("PERFECT_4TH", 5, 5, R.string.interval_perfect4th),
        Interval("TRITONE", 6, 6, R.string.interval_tritone),
        Interval("PERFECT_5TH", 7, 7, R.string.interval_perfect5th),
        Interval("MINOR_6TH", 8, 8, R.string.interval_minor6th),
        Interval("MAJOR_6TH", 9, 9, R.string.interval_major6th),
        Interval("CHORDS.MINOR_7TH", 10, 10, R.string.interval_minor7th),
        Interval("CHORDS.MAJOR_7TH", 11, 11, R.string.interval_major7th),
        Interval("OCATVE", 12, 12, R.string.interval_octave),
        Interval("MINOR_9TH", 13, 13, R.string.interval_minor9th),
        Interval("MAJOR_9TH", 14, 14, R.string.interval_major9th)
    )

    override val musicMap: Map<String, MusicSomething> = list.associateBy { it.name }
}

data class Scale(
    override val name: String,
    override val order: Int,
    val halfSteps: List<Int>,
    override val translation: Int
) : MusicSomething {
    override fun getRange(): Map<String, Int> {
        //todo change name of range
        val range = NOTES_WITH_OCTAVE.size - (halfSteps.maxOrNull() ?: 0)
        return NOTES_WITH_OCTAVE.filter { it.value < range }
    }

    val type = TYPE

    companion object {
        val TYPE = "Scales"
    }

    override fun toNoteIndices(baseNote: Int): List<Int> {
        return halfSteps.map { baseNote + it }
    }
}

object Scales : MusicTerminology {

    override val type: String = Scale.TYPE

    val list = listOf(
        Scale("MAJOR", 1, listOf(0, 2, 4, 5, 7, 9, 11, 12), R.string.scale_major),
        Scale("NATURAL_MINOR", 2, listOf(0, 2, 3, 5, 7, 8, 10, 12), R.string.scale_natural_minor),
        Scale("HARMONIC_MINOR", 3, listOf(0, 2, 3, 5, 7, 8, 11, 12), R.string.scale_harmonic_minor),
    )

    override val musicMap: Map<String, MusicSomething> = list.associateBy { it.name }
}

//
//enum class CHORDS(
//    override val order: Int,
//    val halfSteps: List<Int>,
//    override val translation: Int
//) : MusicTerminology {
//    MAJOR(1, listOf(0, 4, 7), R.string.chord_major),
//    MINOR(2, listOf(0, 3, 7), R.string.chord_minor),
//    DIMINISHED(3, listOf(0, 3, 6), R.string.chord_diminished),
//    AUGMENTED(4, listOf(0, 4, 8), R.string.chord_augmented),
//    MAJOR_7TH(5, listOf(0, 4, 7, 11), R.string.chord_major7th),
//    DOMINANT_7TH(6, listOf(0, 4, 7, 10), R.string.chord_dominant7th),
//    MINOR_7TH(7, listOf(0, 3, 7, 10), R.string.chord_minor7th);
//
//    override val type: String = CHORDS.TYPE
//
//    override fun getRange(): Map<String, Int> =
//        NOTES_WITH_OCTAVE.filter { it.value < NOTES_WITH_OCTAVE.size - (halfSteps.last() - halfSteps.first()) }
//
//
//    companion object {
//        private const val TYPE: String = "Chords"
//        fun getType(): String {
//            return TYPE
//        }
//    }
//}
//
//enum class PERFECT_PITCH(override val order: Int, override val translation: Int) :
//    MusicTerminology {
//    C(1, R.string.C),
//    CIS(2, R.string.Cis),
//    D(3, R.string.D),
//    DIS(4, R.string.Dis),
//    E(5, R.string.E),
//    F(6, R.string.F),
//    FIS(7, R.string.Fis),
//    G(8, R.string.G),
//    GIS(9, R.string.Gis),
//    A(10, R.string.A),
//    AIS(11, R.string.Ais),
//    B(12, R.string.B);
//
//    override val type: String = PERFECT_PITCH.TYPE
//
//    override fun getRange(): Map<String, Int> = NOTES_WITH_OCTAVE
//
//    companion object {
//        private const val TYPE: String = "PerfectPitch"
//        fun getType(): String {
//            return TYPE
//        }
//    }
//}

val NOTES = mapOf(
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

val NOTES_WITH_OCTAVE = mapOf(
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

//    MINOR_2ND(1, 1, R.string.interval_minor2nd),
//    MAJOR_2RD(2, 2, R.string.interval_major2nd),
//    MINOR_3RD(3, 3, R.string.interval_minor3rd),
//    MAJOR_3RD(4, 4, R.string.interval_major3rd),
//    PERFECT_4TH(5, 5, R.string.interval_perfect4th),
//    TRITONE(6, 6, R.string.interval_tritone),
//    PERFECT_5TH(7, 7, R.string.interval_perfect5th),
//    MINOR_6TH(8, 8, R.string.interval_minor6th),
//    MAJOR_6TH(9, 9, R.string.interval_major6th),
//    MINOR_7TH(10, 10, R.string.interval_minor7th),
//    MAJOR_7TH(11, 11, R.string.interval_major7th),
//    OCATVE(12, 12, R.string.interval_octave),
//    MINOR_9TH(13, 13, R.string.interval_minor9th),
//    MAJOR_9TH(14, 14, R.string.interval_major9th);

//MAJOR(1, listOf(0, 2, 4, 5, 7, 9, 11, 12), R.string.scale_major),
//NATURAL_MINOR(2, listOf(0, 2, 3, 5, 7, 8, 10, 12), R.string.scale_natural_minor),
//HARMONIC_MINOR(3, listOf(0, 2, 3, 5, 7, 8, 11, 12), R.string.scale_harmonic_minor);
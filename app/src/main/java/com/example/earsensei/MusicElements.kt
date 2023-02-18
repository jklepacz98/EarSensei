package com.example.earsensei

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class MusicElementType(val name: String) : Parcelable {
    @Parcelize
    object Intervals : MusicElementType(INTERVALS)
    object Chords : MusicElementType(CHORDS)
    object Scales : MusicElementType(SCALES)

    private companion object {
        const val INTERVALS = "Intervals"
        const val CHORDS = "Chords"
        const val SCALES = "Scales"
    }
}

object MusicElementsFactory {
    fun get(musicElementType: MusicElementType): MusicElements = when (musicElementType) {
        is MusicElementType.Intervals -> Intervals
        is MusicElementType.Chords -> Chords
        is MusicElementType.Scales -> Scales
    }
}

interface MusicElements {
    val musicElementsList: List<MusicElement>
    val musicElementType: MusicElementType
}

interface MusicElement {
    val name: String
    val order: Int
    val stringResourceId: Int
    val musicElementType: MusicElementType
    fun baseNoteRange(): Map<String, Int>
    fun toNoteIds(baseNote: Int): List<Int>
}


data class Interval(
    override val name: String,
    override val order: Int,
    override val stringResourceId: Int,
    val halfSteps: Int,
) : MusicElement {
    override fun baseNoteRange() =
        NOTES_WITH_OCTAVE.filter { it.value < NOTES_WITH_OCTAVE.size - halfSteps }

    override val musicElementType = MusicElementType.Intervals

    override fun toNoteIds(baseNote: Int): List<Int> {
        val secondNote = baseNote + halfSteps
        return listOf(baseNote, secondNote)
    }
}

object Intervals : MusicElements {

    override val musicElementType = MusicElementType.Intervals

    override val musicElementsList = listOf(
        Interval("MINOR_2ND", 1, R.string.interval_minor2nd, 1),
        Interval("MAJOR_2RD", 2, R.string.interval_major2nd, 2),
        Interval("MINOR_3RD", 3, R.string.interval_minor3rd, 3),
        Interval("MAJOR_3RD", 4, R.string.interval_major3rd, 4),
        Interval("PERFECT_4TH", 5, R.string.interval_perfect4th, 5),
        Interval("TRITONE", 6, R.string.interval_tritone, 6),
        Interval("PERFECT_5TH", 7, R.string.interval_perfect5th, 7),
        Interval("MINOR_6TH", 8, R.string.interval_minor6th, 8),
        Interval("MAJOR_6TH", 9, R.string.interval_major6th, 9),
        Interval("CHORDS.MINOR_7TH", 10, R.string.interval_minor7th, 10),
        Interval("CHORDS.MAJOR_7TH", 11, R.string.interval_major7th, 11),
        Interval("OCATVE", 12, R.string.interval_octave, 12),
        Interval("MINOR_9TH", 13, R.string.interval_minor9th, 13),
        Interval("MAJOR_9TH", 14, R.string.interval_major9th, 14),
    )
}


data class Chord(
    override val name: String,
    override val order: Int,
    override val stringResourceId: Int,
    val halfSteps: List<Int>,
) : MusicElement {
    override fun baseNoteRange(): Map<String, Int> {
        val range = NOTES_WITH_OCTAVE.size - (halfSteps.maxOrNull() ?: 0)
        return NOTES_WITH_OCTAVE.filter { it.value < range }
    }

    override val musicElementType = MusicElementType.Chords

    override fun toNoteIds(baseNote: Int): List<Int> {
        return halfSteps.map { baseNote + it }
    }
}

object Chords : MusicElements {

    override val musicElementType = MusicElementType.Chords
    override val musicElementsList =
        listOf(Chord("MAJOR", 1, R.string.chord_major, listOf(0, 4, 7)),
            Chord("MINOR", 2, R.string.chord_minor, listOf(0, 3, 7)),
            Chord("DIMINISHED", 3, R.string.chord_diminished, listOf(0, 3, 6)),
            Chord("AUGMENTED", 4, R.string.chord_augmented, listOf(0, 4, 8)),
            Chord("MAJOR_7TH", 5, R.string.chord_major7th, listOf(0, 4, 7, 11)),
            Chord("DOMINANT_7TH", 6, R.string.chord_dominant7th, listOf(0, 4, 7, 10)),
            Chord("MINOR_7TH", 7, R.string.chord_minor7th, listOf(0, 3, 7, 10)))
}

data class Scale(
    override val name: String,
    override val order: Int,
    override val stringResourceId: Int,
    val halfSteps: List<Int>,
) : MusicElement {

    override val musicElementType = MusicElementType.Scales

    override fun baseNoteRange(): Map<String, Int> {
        val range = NOTES_WITH_OCTAVE.size - (halfSteps.maxOrNull() ?: 0)
        return NOTES_WITH_OCTAVE.filter { it.value < range }
    }

    override fun toNoteIds(baseNote: Int): List<Int> {
        return halfSteps.map { baseNote + it }
    }
}

object Scales : MusicElements {

    override val musicElementType = MusicElementType.Scales

    override val musicElementsList = listOf(
        Scale("MAJOR", 1, R.string.scale_major, listOf(0, 2, 4, 6, 7, 9, 11, 12)),
        Scale("MAJOR_PENTATONIC", 2, R.string.scale_pentatonic_major, listOf(0, 2, 4, 7, 9, 12)),
        Scale("BLUES_MAJOR", 3, R.string.scale_blues_major, listOf(0, 3, 5, 6, 7, 9, 12)),
        Scale("NATURAL_MINOR", 4, R.string.scale_natural_minor, listOf(0, 2, 3, 5, 7, 8, 10, 12)),
        Scale(
            "MELODIC_MINOR",
            5,
            R.string.scale_melodic_minor,
            listOf(0, 2, 3, 5, 7, 9, 11, 12, 10, 8, 7, 5, 3, 2, 0),
        ),
        Scale("HARMONIC_MINOR", 6, R.string.scale_harmonic_minor, listOf(0, 2, 3, 5, 7, 8, 11, 12)),
        Scale("BLUES_MINOR", 7, R.string.scale_blues_minor, listOf(0, 3, 5, 6, 7, 10, 12)),
    )
}


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

    "C6" to 36,
)

//        PerfectPitch("C", 1, 1, R.string.C),
//        PerfectPitch("CIS", 2, 2, R.string.Cis),
//        PerfectPitch("D", 3, 3, R.string.D),
//        PerfectPitch("DIS", 4, 4, R.string.Dis),
//        PerfectPitch("E", 5, 5, R.string.E),
//        PerfectPitch("F", 6, 6, R.string.F),
//        PerfectPitch("FIS", 7, 7, R.string.Fis),
//        PerfectPitch("G", 8, 8, R.string.G),
//        PerfectPitch("GIS", 9, 9, R.string.Gis),
//        PerfectPitch("A", 10, 10, R.string.A),
//        PerfectPitch("AIS", 11, 11, R.string.Ais),
//        PerfectPitch("B", 12, 12, R.string.B)
package com.example.earsensei.graphdatapreparers

import com.example.earsensei.QuizRatioCalculator
import com.example.earsensei.database.quizResult.QuizResult
import com.example.earsensei.utils.QuizType
import org.junit.Assert.assertEquals
import org.junit.Test

class QuizRatioCalculatorTest {

    val quizResultModelsAllWrongs: ArrayList<QuizResult> = arrayListOf(
        QuizResult(0, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0),
        QuizResult(1, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0),
        QuizResult(2, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0),
        QuizResult(3, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0),
        QuizResult(4, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0)
    )

    val quizResultModelsAllCorrects: ArrayList<QuizResult> = arrayListOf(
        QuizResult(0, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0),
        QuizResult(1, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0),
        QuizResult(2, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0),
        QuizResult(3, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0),
        QuizResult(4, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0)
    )

    val quizResultModelsTwoOfFiveCorrects: ArrayList<QuizResult> = arrayListOf(
        QuizResult(0, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0),
        QuizResult(1, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0),
        QuizResult(2, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0),
        QuizResult(3, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0),
        QuizResult(4, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0)
    )

    @Test
    fun `Correct Ratio - Ratio should be 0`() {
        val ratio: Float =
            QuizRatioCalculator.calculateCorrectAnswerRatio(quizResultModelsAllWrongs)
        assertEquals(0F, ratio)
    }

    @Test
    fun `Correct Ratio - Ratio should be 1`() {
        val ratio: Float =
            QuizRatioCalculator.calculateCorrectAnswerRatio(quizResultModelsAllCorrects)
        assertEquals(1F, ratio)
    }

    @Test
    fun `Correct Ratio - Ratio should be four tenths`() {
        val ratio: Float =
            QuizRatioCalculator.calculateCorrectAnswerRatio(quizResultModelsTwoOfFiveCorrects)
        assertEquals(0.4F, ratio)
    }

    @Test
    fun `Specific Answer Ratio - Ratio should be 0`() {
        val ratio: Float = QuizRatioCalculator.calculateSpecificAnswerRatio(
            quizResultModelsAllCorrects,
            "some other answer"
        )
        assertEquals(0F, ratio)
    }

    @Test
    fun `CSpecific Answer Ratio  - Ratio should be 1`() {
        val ratio: Float = QuizRatioCalculator.calculateSpecificAnswerRatio(
            quizResultModelsAllCorrects,
            "goodAnswer"
        )
        assertEquals(1F, ratio)
    }

    @Test
    fun `Specific Answer Ratio - Ratio should be four tenths`() {
        val ratio: Float = QuizRatioCalculator.calculateSpecificAnswerRatio(
            quizResultModelsTwoOfFiveCorrects,
            "goodAnswer"
        )
        assertEquals(0.4F, ratio)
    }
}
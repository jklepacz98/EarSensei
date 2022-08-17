package com.example.earsensei.graphdatapreparers

import com.example.earsensei.QuizRatioCalculator
import com.example.earsensei.QuizType
import com.example.earsensei.database.result.Result
import org.junit.Assert.assertEquals
import org.junit.Test

class QuizRatioCalculatorTest {

    val resultModelsAllWrongs: ArrayList<Result> = arrayListOf(
        Result(0, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0),
        Result(1, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0),
        Result(2, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0),
        Result(3, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0),
        Result(4, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0)
    )

    val resultModelsAllCorrects: ArrayList<Result> = arrayListOf(
        Result(0, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0),
        Result(1, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0),
        Result(2, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0),
        Result(3, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0),
        Result(4, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0)
    )

    val resultModelsTwoOfFiveCorrects: ArrayList<Result> = arrayListOf(
        Result(0, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0),
        Result(1, QuizType.INTERVALS, "baseNote", "goodAnswer", "goodAnswer", 0),
        Result(2, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0),
        Result(3, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0),
        Result(4, QuizType.INTERVALS, "baseNote", "goodAnswer", "badAnswer", 0)
    )

    @Test
    fun `Correct Ratio - Ratio should be 0`() {
        val ratio: Float =
            QuizRatioCalculator.calculateCorrectAnswerRatio(resultModelsAllWrongs)
        assertEquals(0F, ratio)
    }

    @Test
    fun `Correct Ratio - Ratio should be 1`() {
        val ratio: Float =
            QuizRatioCalculator.calculateCorrectAnswerRatio(resultModelsAllCorrects)
        assertEquals(1F, ratio)
    }

    @Test
    fun `Correct Ratio - Ratio should be four tenths`() {
        val ratio: Float =
            QuizRatioCalculator.calculateCorrectAnswerRatio(resultModelsTwoOfFiveCorrects)
        assertEquals(0.4F, ratio)
    }

    @Test
    fun `Specific Answer Ratio - Ratio should be 0`() {
        val ratio: Float = QuizRatioCalculator.calculateSpecificAnswerRatio(
            resultModelsAllCorrects,
            "some other answer"
        )
        assertEquals(0F, ratio)
    }

    @Test
    fun `CSpecific Answer Ratio  - Ratio should be 1`() {
        val ratio: Float = QuizRatioCalculator.calculateSpecificAnswerRatio(
            resultModelsAllCorrects,
            "goodAnswer"
        )
        assertEquals(1F, ratio)
    }

    @Test
    fun `Specific Answer Ratio - Ratio should be four tenths`() {
        val ratio: Float = QuizRatioCalculator.calculateSpecificAnswerRatio(
            resultModelsTwoOfFiveCorrects,
            "goodAnswer"
        )
        assertEquals(0.4F, ratio)
    }
}
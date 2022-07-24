package com.example.earsensei.graphdatapreparers

import com.example.earsensei.QuizType
import com.example.earsensei.QuizRatioCalculator
import com.example.earsensei.dbmodels.QuizRecordModel
import org.junit.Assert.*
import org.junit.Test

class QuizRatioCalculatorTest{


    val quizRecordModelsAllWrongs : ArrayList<QuizRecordModel> = arrayListOf(
        QuizRecordModel(0, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
        QuizRecordModel(1, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
        QuizRecordModel(2, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
        QuizRecordModel(3, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
        QuizRecordModel(4, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0)
    )

    val quizRecordModelsAllCorrects : ArrayList<QuizRecordModel> = arrayListOf(
        QuizRecordModel(0, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
        QuizRecordModel(1, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
        QuizRecordModel(2, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
        QuizRecordModel(3, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
        QuizRecordModel(4, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0)
    )

    val quizRecordModelsTwoOfFiveCorrects : ArrayList<QuizRecordModel> = arrayListOf(
        QuizRecordModel(0, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
        QuizRecordModel(1, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
        QuizRecordModel(2, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
        QuizRecordModel(3, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
        QuizRecordModel(4, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0)
    )

    @Test
    fun `Correct Ratio - Ratio should be 0`(){
        val ratio : Float = QuizRatioCalculator.calculateCorrectAnswerRatio(quizRecordModelsAllWrongs)
        assertEquals(0F, ratio)
    }

    @Test
    fun `Correct Ratio - Ratio should be 1`(){
        val ratio : Float = QuizRatioCalculator.calculateCorrectAnswerRatio(quizRecordModelsAllCorrects)
        assertEquals(1F, ratio)
    }

    @Test
    fun `Correct Ratio - Ratio should be four tenths`(){
        val ratio : Float = QuizRatioCalculator.calculateCorrectAnswerRatio(quizRecordModelsTwoOfFiveCorrects)
        assertEquals(0.4F, ratio)
    }

    @Test
    fun `Specific Answer Ratio - Ratio should be 0`(){
        val ratio : Float = QuizRatioCalculator.calculateSpecificAnswerRatio(quizRecordModelsAllCorrects, "some other answer")
        assertEquals(0F, ratio)
    }

    @Test
    fun `CSpecific Answer Ratio  - Ratio should be 1`(){
        val ratio : Float = QuizRatioCalculator.calculateSpecificAnswerRatio(quizRecordModelsAllCorrects, "goodAnswer")
        assertEquals(1F, ratio)
    }

    @Test
    fun `Specific Answer Ratio - Ratio should be four tenths`(){
        val ratio : Float = QuizRatioCalculator.calculateSpecificAnswerRatio(quizRecordModelsTwoOfFiveCorrects, "goodAnswer")
        assertEquals(0.4F, ratio)
    }
}
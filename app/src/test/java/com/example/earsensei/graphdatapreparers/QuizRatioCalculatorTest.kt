package com.example.earsensei.graphdatapreparers

import com.example.earsensei.QuizType
import com.example.earsensei.QuizRatioCalculator
import com.example.earsensei.dbmodels.QuizModel
import org.junit.Assert.*
import org.junit.Test

class QuizRatioCalculatorTest{


    val quizModelsAllWrong : ArrayList<QuizModel> = arrayListOf(
        QuizModel(0, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
        QuizModel(1, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
        QuizModel(2, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
        QuizModel(3, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
        QuizModel(4, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0)
    )

    val quizModelsAllCorrect : ArrayList<QuizModel> = arrayListOf(
        QuizModel(0, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
        QuizModel(1, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
        QuizModel(2, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
        QuizModel(3, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
        QuizModel(4, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0)
    )

    val quizModelsTwoOfFiveCorrect : ArrayList<QuizModel> = arrayListOf(
        QuizModel(0, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
        QuizModel(1, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
        QuizModel(2, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
        QuizModel(3, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
        QuizModel(4, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0)
    )

    @Test
    fun `Correct Ratio - Ratio should be 0`(){
        val ratio : Float = QuizRatioCalculator.calculateCorrectAnswerRatio(quizModelsAllWrong)
        assertEquals(0F, ratio)
    }

    @Test
    fun `Correct Ratio - Ratio should be 1`(){
        val ratio : Float = QuizRatioCalculator.calculateCorrectAnswerRatio(quizModelsAllCorrect)
        assertEquals(1F, ratio)
    }

    @Test
    fun `Correct Ratio - Ratio should be four tenths`(){
        val ratio : Float = QuizRatioCalculator.calculateCorrectAnswerRatio(quizModelsTwoOfFiveCorrect)
        assertEquals(0.4F, ratio)
    }

    @Test
    fun `Specific Answer Ratio - Ratio should be 0`(){
        val ratio : Float = QuizRatioCalculator.calculateSpecificAnswerRatio(quizModelsAllCorrect, "some other answer")
        assertEquals(0F, ratio)
    }

    @Test
    fun `CSpecific Answer Ratio  - Ratio should be 1`(){
        val ratio : Float = QuizRatioCalculator.calculateSpecificAnswerRatio(quizModelsAllCorrect, "goodAnswer")
        assertEquals(1F, ratio)
    }

    @Test
    fun `Specific Answer Ratio - Ratio should be four tenths`(){
        val ratio : Float = QuizRatioCalculator.calculateSpecificAnswerRatio(quizModelsTwoOfFiveCorrect, "goodAnswer")
        assertEquals(0.4F, ratio)
    }
}
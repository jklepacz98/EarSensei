package com.example.earsensei.graphdatapreparers

import com.example.earsensei.QuizType
import com.example.earsensei.SpecificAnswerToAllAnswersRatio
import com.example.earsensei.dbmodels.QuizModel
import org.junit.Assert.*
import org.junit.Test

class SpecificAnswerToAllAnswersRatioTest{

    val specificAnswerToAllAnswersRatio : SpecificAnswerToAllAnswersRatio = SpecificAnswerToAllAnswersRatio()

    @Test
    fun `Ratio should be 0`(){

        val quizModels : ArrayList<QuizModel> = arrayListOf(
            QuizModel(0, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizModel(1, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizModel(2, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizModel(3, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizModel(4, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0)
        )
        val ratio = specificAnswerToAllAnswersRatio.calculateRatio(quizModels, "userAnswer2")
        assertEquals(0F, ratio)
    }

    @Test
    fun `Ratio should be 1`(){
        val quizModels : ArrayList<QuizModel> = arrayListOf(
            QuizModel(0, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizModel(1, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizModel(2, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizModel(3, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizModel(4, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0)
        )
        val ratio = specificAnswerToAllAnswersRatio.calculateRatio(quizModels, "userAnswer1")
        assertEquals(1F, ratio)
    }

    @Test
    fun `Ratio should be two tenths`(){
        val quizModels : ArrayList<QuizModel> = arrayListOf(
            QuizModel(0, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizModel(1, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer2", 0),
            QuizModel(2, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer2", 0),
            QuizModel(3, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer2", 0),
            QuizModel(4, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer2", 0)
        )
        val ratio = specificAnswerToAllAnswersRatio.calculateRatio(quizModels, "userAnswer1")
        assertEquals(0.2F, ratio)
    }
}
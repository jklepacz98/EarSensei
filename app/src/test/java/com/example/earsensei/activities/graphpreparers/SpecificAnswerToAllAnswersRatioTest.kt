package com.example.earsensei.activities.graphpreparers

import com.example.earsensei.CorrectToAllAnswersRatio
import com.example.earsensei.QuizType
import com.example.earsensei.SpecificAnswerToAllAnswersRatio
import com.example.earsensei.dbmodels.QuizRecordModel
import org.junit.Assert.*
import org.junit.Test

class SpecificAnswerToAllAnswersRatioTest{

    val specificAnswerToAllAnswersRatio : SpecificAnswerToAllAnswersRatio = SpecificAnswerToAllAnswersRatio()

    @Test
    fun `Ratio should be 0`(){

        val quizRecordModels : ArrayList<QuizRecordModel> = arrayListOf(
            QuizRecordModel(0, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizRecordModel(1, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizRecordModel(2, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizRecordModel(3, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizRecordModel(4, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0)
        )
        val ratio = specificAnswerToAllAnswersRatio.calculateRatio(quizRecordModels, "userAnswer2")
        assertEquals(0F, ratio)
    }

    @Test
    fun `Ratio should be 1`(){
        val quizRecordModels : ArrayList<QuizRecordModel> = arrayListOf(
            QuizRecordModel(0, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizRecordModel(1, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizRecordModel(2, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizRecordModel(3, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizRecordModel(4, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0)
        )
        val ratio = specificAnswerToAllAnswersRatio.calculateRatio(quizRecordModels, "userAnswer1")
        assertEquals(1F, ratio)
    }

    @Test
    fun `Ratio should be two tenths`(){
        val quizRecordModels : ArrayList<QuizRecordModel> = arrayListOf(
            QuizRecordModel(0, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer1", 0),
            QuizRecordModel(1, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer2", 0),
            QuizRecordModel(2, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer2", 0),
            QuizRecordModel(3, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer2", 0),
            QuizRecordModel(4, QuizType.INTERVALS,"baseNote", "correctAnswer", "userAnswer2", 0)
        )
        val ratio = specificAnswerToAllAnswersRatio.calculateRatio(quizRecordModels, "userAnswer1")
        assertEquals(0.2F, ratio)
    }
}
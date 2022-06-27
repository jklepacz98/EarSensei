package com.example.earsensei.activities.graphpreparers

import com.example.earsensei.CorrectToAllAnswersRatio
import com.example.earsensei.QuizType
import com.example.earsensei.dbmodels.QuizModel
import org.junit.Assert.*
import org.junit.Test
import kotlin.collections.ArrayList

class CorrectToAllAnswersRatioTest{
    @Test
    fun `Ratio should be 0`(){

        val quizModels : ArrayList<QuizModel> = arrayListOf(
            QuizModel(0, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizModel(1, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizModel(2, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizModel(3, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizModel(4, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0)
        )
        val correctToAllAnswersRatio : CorrectToAllAnswersRatio = CorrectToAllAnswersRatio()
        val ratio : Float = correctToAllAnswersRatio.calculateRatio(quizModels)
        assertEquals(0F, ratio)
    }

    @Test
    fun `Ratio should be 1`(){
        val quizModels : ArrayList<QuizModel> = arrayListOf(
            QuizModel(0, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
            QuizModel(1, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
            QuizModel(2, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
            QuizModel(3, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
            QuizModel(4, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0)
        )
        val correctToAllAnswersRatio : CorrectToAllAnswersRatio = CorrectToAllAnswersRatio()
        val ratio : Float = correctToAllAnswersRatio.calculateRatio(quizModels)
        assertEquals(1F, ratio)
    }

    @Test
    fun `Ratio should be two tenths`(){
        val quizModels : ArrayList<QuizModel> = arrayListOf(
            QuizModel(0, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
            QuizModel(1, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizModel(2, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizModel(3, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizModel(4, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0)
        )
        val correctToAllAnswersRatio : CorrectToAllAnswersRatio = CorrectToAllAnswersRatio()
        val ratio : Float = correctToAllAnswersRatio.calculateRatio(quizModels)
        assertEquals(0.2F, ratio)
    }
}
package com.example.earsensei.activities.graphpreparers

import com.example.earsensei.QuizType
import com.example.earsensei.dbmodels.QuizRecordModel
import org.junit.Assert.*
import org.junit.Test
import kotlin.collections.ArrayList

class GraphPreparerTest{
    @Test
    fun `Ratio should be 0`(){

        val quizRecordModels : ArrayList<QuizRecordModel> = arrayListOf(
            QuizRecordModel(0, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizRecordModel(1, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizRecordModel(2, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizRecordModel(3, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizRecordModel(4, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0)
        )
        val xLabels : ArrayList<String> = arrayListOf()
        val graphPreparer : GraphPreparer = GraphPreparer(quizRecordModels, xLabels,{quizRecordModel : QuizRecordModel -> quizRecordModel.correctAnswer})
        val ratio : Float = graphPreparer.correctAnswerRatio(quizRecordModels)
        assertEquals(0F, ratio)
    }

    @Test
    fun `Ratio should be 1`(){
        val quizRecordModels : ArrayList<QuizRecordModel> = arrayListOf(
            QuizRecordModel(0, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
            QuizRecordModel(1, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
            QuizRecordModel(2, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
            QuizRecordModel(3, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
            QuizRecordModel(4, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0)
        )
        val xLabels : ArrayList<String> = arrayListOf()
        val graphPreparer : GraphPreparer = GraphPreparer(quizRecordModels, xLabels, {quizRecordModel : QuizRecordModel -> quizRecordModel.correctAnswer})
        val ratio : Float = graphPreparer.correctAnswerRatio(quizRecordModels)
        assertEquals(1F, ratio)
    }

    @Test
    fun `Ratio should be two tenths`(){
        val quizRecordModels : ArrayList<QuizRecordModel> = arrayListOf(
            QuizRecordModel(0, QuizType.INTERVALS,"baseNote", "goodAnswer", "goodAnswer", 0),
            QuizRecordModel(1, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizRecordModel(2, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizRecordModel(3, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0),
            QuizRecordModel(4, QuizType.INTERVALS,"baseNote", "goodAnswer", "badAnswer", 0)
        )
        val xLabels : ArrayList<String> = arrayListOf()
        val graphPreparer : GraphPreparer = GraphPreparer(quizRecordModels, xLabels, {quizRecordModel : QuizRecordModel -> quizRecordModel.correctAnswer})
        val ratio : Float = graphPreparer.correctAnswerRatio(quizRecordModels)
        assertEquals(0.2F, ratio)
    }
}
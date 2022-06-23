package com.example.earsensei.activities.graphpreparers

import com.example.earsensei.dbmodels.TestModel
import org.junit.Assert.*
import org.junit.Test
import kotlin.collections.ArrayList

class GraphPreparerTest{
    @Test
    fun `Ratio should be 0`(){

        val testModels : ArrayList<TestModel> = arrayListOf(
            TestModel(0, "baseNote", "goodAnswer", "badAnswer", 0),
            TestModel(1, "baseNote", "goodAnswer", "badAnswer", 0),
            TestModel(2, "baseNote", "goodAnswer", "badAnswer", 0),
            TestModel(3, "baseNote", "goodAnswer", "badAnswer", 0),
            TestModel(4, "baseNote", "goodAnswer", "badAnswer", 0)
        )
        val xLabels : ArrayList<String> = arrayListOf()
        val graphPreparer : GraphPreparer = GraphPreparer(testModels, xLabels)
        val ratio : Float = graphPreparer.correctAnswerRatio(testModels)
        assertEquals(0F, ratio)
    }

    @Test
    fun `Ratio should be 1`(){
        val testModels : ArrayList<TestModel> = arrayListOf(
            TestModel(0, "baseNote", "goodAnswer", "goodAnswer", 0),
            TestModel(1, "baseNote", "goodAnswer", "goodAnswer", 0),
            TestModel(2, "baseNote", "goodAnswer", "goodAnswer", 0),
            TestModel(3, "baseNote", "goodAnswer", "goodAnswer", 0),
            TestModel(4, "baseNote", "goodAnswer", "goodAnswer", 0)
        )
        val xLabels : ArrayList<String> = arrayListOf()
        val graphPreparer : GraphPreparer = GraphPreparer(testModels, xLabels)
        val ratio : Float = graphPreparer.correctAnswerRatio(testModels)
        assertEquals(1F, ratio)
    }

    @Test
    fun `Ratio should be two tenths`(){
        val testModels : ArrayList<TestModel> = arrayListOf(
            TestModel(0, "baseNote", "goodAnswer", "goodAnswer", 0),
            TestModel(1, "baseNote", "goodAnswer", "badAnswer", 0),
            TestModel(2, "baseNote", "goodAnswer", "badAnswer", 0),
            TestModel(3, "baseNote", "goodAnswer", "badAnswer", 0),
            TestModel(4, "baseNote", "goodAnswer", "badAnswer", 0)
        )
        val xLabels : ArrayList<String> = arrayListOf()
        val graphPreparer : GraphPreparer = GraphPreparer(testModels, xLabels)
        val ratio : Float = graphPreparer.correctAnswerRatio(testModels)
        assertEquals(0.2F, ratio)
    }
}
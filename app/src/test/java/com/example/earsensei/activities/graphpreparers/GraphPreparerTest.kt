package com.example.earsensei.activities.graphpreparers

import com.example.earsensei.dbmodels.IntervalModel
import com.example.earsensei.dbmodels.TestModel
import org.junit.Assert.*
import org.junit.Test
import kotlin.collections.ArrayList

class GraphPreparerTest{
    @Test
    fun `Ratio should be 0`(){

        val intervalModels : ArrayList<IntervalModel> = arrayListOf(
            IntervalModel(0, "baseNote", "goodAnswer", "badAnswer", 0),
            IntervalModel(1, "baseNote", "goodAnswer", "badAnswer", 0),
            IntervalModel(2, "baseNote", "goodAnswer", "badAnswer", 0),
            IntervalModel(3, "baseNote", "goodAnswer", "badAnswer", 0),
            IntervalModel(4, "baseNote", "goodAnswer", "badAnswer", 0)
        )
        val xLabels : ArrayList<String> = arrayListOf()
        val graphPreparer : GraphPreparer = GraphPreparer(intervalModels, xLabels)
        val ratio : Float = graphPreparer.correctAnswerRatio(intervalModels)
        assertEquals(0F, ratio)
    }

    @Test
    fun `Ratio should be 1`(){
        val intervalModels : ArrayList<IntervalModel> = arrayListOf(
            IntervalModel(0, "baseNote", "goodAnswer", "goodAnswer", 0),
            IntervalModel(1, "baseNote", "goodAnswer", "goodAnswer", 0),
            IntervalModel(2, "baseNote", "goodAnswer", "goodAnswer", 0),
            IntervalModel(3, "baseNote", "goodAnswer", "goodAnswer", 0),
            IntervalModel(4, "baseNote", "goodAnswer", "goodAnswer", 0)
        )
        val xLabels : ArrayList<String> = arrayListOf()
        val graphPreparer : GraphPreparer = GraphPreparer(intervalModels, xLabels)
        val ratio : Float = graphPreparer.correctAnswerRatio(intervalModels)
        assertEquals(1F, ratio)
    }

    @Test
    fun `Ratio should be two tenths`(){
        val intervalModels : ArrayList<IntervalModel> = arrayListOf(
            IntervalModel(0, "baseNote", "goodAnswer", "goodAnswer", 0),
            IntervalModel(1, "baseNote", "goodAnswer", "badAnswer", 0),
            IntervalModel(2, "baseNote", "goodAnswer", "badAnswer", 0),
            IntervalModel(3, "baseNote", "goodAnswer", "badAnswer", 0),
            IntervalModel(4, "baseNote", "goodAnswer", "badAnswer", 0)
        )
        val xLabels : ArrayList<String> = arrayListOf()
        val graphPreparer : GraphPreparer = GraphPreparer(intervalModels, xLabels)
        val ratio : Float = graphPreparer.correctAnswerRatio(intervalModels)
        assertEquals(0.2F, ratio)
    }
}
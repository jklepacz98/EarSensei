package com.example.earsensei.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.earsensei.*
import com.example.earsensei.activities.graphpreparers.TestGraphPreparer
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarEntry

class StatsDetailsActivity : AppCompatActivity() {

    val earSenseiDBHelper : EarSenseiDBHelper = EarSenseiDBHelper(this)


    companion object{
        val barDistance : Float = 1F
        val barWidth : Float = 0.9F
        val maxVisibleXAxisLabels : Int = 20
        val animationLengthInMs : Int = 750
        val yAxisMinimum : Float = 0F
        val yAxisMaximum : Float = 1F + 0.001F //0.001F addition is to show last grid line
        val rightOffset : Float = 32F
        val xAxisLabelTextSize : Float = 16F
        val barTextSize : Float = 12F
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_details)


        val filter : String = intent.getStringExtra("FILTER") ?: "Extra was null"
        actionBar?.setTitle(filter)
        supportActionBar?.setTitle(filter)



        val plotDataPreparer : TestGraphPreparer = TestGraphPreparer(earSenseiDBHelper.readAllTestData(), ArrayList(
            Note.notes.keys))

        val barChart : BarChart = findViewById(R.id.bar_chart)
        val barChartManager : BarChartManager = BarChartManager(barChart)
        val dataValues1 : ArrayList<BarEntry> = arrayListOf()
        val ratioHashMap : LinkedHashMap<String, Float> = plotDataPreparer.prepareHashMap()



        var iterator : Float = 0F
        ratioHashMap.forEach(){
            dataValues1.add(BarEntry(iterator, it.value))
            iterator+= StatsGeneralActivity.barDistance
        }

        val xAxisLabels : ArrayList<String> = ArrayList(ratioHashMap.keys)

        barChartManager.setupBarChart()
        barChartManager.setXAxisLabels(xAxisLabels)
        barChartManager.setDataValues(dataValues1)
    }
}
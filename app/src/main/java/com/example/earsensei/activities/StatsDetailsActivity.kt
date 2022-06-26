package com.example.earsensei.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.earsensei.*
import com.example.earsensei.activities.graphpreparers.GraphPreparer
import com.example.earsensei.dbmodels.QuizRecordModel
import com.example.earsensei.dbmodels.TestModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarEntry

class StatsDetailsActivity : AppCompatActivity() {

    val earSenseiDBHelper : EarSenseiDBHelper = EarSenseiDBHelper(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_details)


//        val filter : String = intent.getStringExtra("FILTER") ?: "Extra was null"
//        actionBar?.setTitle(filter)
//        supportActionBar?.setTitle(filter)
//
//
//        val filteredIntervalsData : ArrayList<QuizRecordModel> = ArrayList(earSenseiDBHelper.readAllIntervalsData().filter { it.correctAnswer == filter })
//
//        val plotDataPreparer : GraphPreparer = GraphPreparer(filteredIntervalsData, ArrayList(
//            Note.intervals.keys), {quizRecordModel : QuizRecordModel -> quizRecordModel.userAnswer})
//
//        val barChart : BarChart = findViewById(R.id.bar_chart)
//        val barChartManager : BarChartManager = BarChartManager(barChart)
//        val ratioHashMap : LinkedHashMap<String, Float> = plotDataPreparer.prepareHashMap()
//
//
//
//        val xAxisLabels : ArrayList<String> = ArrayList(ratioHashMap.keys)
//
//        barChartManager.setupBarChart()
//        barChartManager.setXAxisLabels(xAxisLabels)
//        barChartManager.setDataValues(ArrayList(ratioHashMap.values))
    }
}
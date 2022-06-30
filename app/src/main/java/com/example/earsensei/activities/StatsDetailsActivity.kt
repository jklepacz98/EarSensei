package com.example.earsensei.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.earsensei.*
import com.example.earsensei.dbmodels.QuizModel
import com.example.earsensei.graphdatapreparers.DetailsGraphDataPreparer
import com.github.mikephil.charting.charts.BarChart

class StatsDetailsActivity : AppCompatActivity() {

    val earSenseiDBHelper : EarSenseiDBHelper = EarSenseiDBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_details)

        val filter : String = intent.getStringExtra("FILTER") ?: "Extra was null"
        actionBar?.setTitle(filter)
        supportActionBar?.setTitle(filter)

        val filteredIntervalsData : ArrayList<QuizModel> = ArrayList(earSenseiDBHelper.readAllIntervalsData())

        val graphDataPreparer : DetailsGraphDataPreparer = DetailsGraphDataPreparer(filteredIntervalsData, ArrayList(
            MusicTerminology.intervals.keys), { quizModel : QuizModel -> quizModel.correctAnswer}, filter)
        val barChart : BarChart = findViewById(R.id.bar_chart)
        val ratioHashMap : LinkedHashMap<String, Float> = graphDataPreparer.prepareXYHashMap()
        val barChartManager : BarChartManager = BarChartManager(barChart)

        val xAxisLabels : ArrayList<String> = ArrayList(ratioHashMap.keys)

        barChartManager.setupBarChart()
        barChartManager.setXAxisLabels(xAxisLabels)
        barChartManager.setDataValues(ArrayList(ratioHashMap.values))
    }
}
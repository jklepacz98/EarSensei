package com.example.earsensei.graphs

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.earsensei.MusicTerminology
import com.example.earsensei.R
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.database.quizResult.QuizResultTestDataset
import com.example.earsensei.graphdatapreparers.GraphDataPreparer
import com.example.earsensei.utils.QuizType
import com.github.mikephil.charting.charts.BarChart

class StatsGeneralActivity : AppCompatActivity() {

    val quizResults = QuizResultTestDataset.generateQuizResults()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_general)

        val db = EarSenseiDatabase.getDataBase(this)

        val actionBarTitle: String? = intent.getStringExtra("TITLE")
        actionBar?.title = actionBarTitle
        supportActionBar?.title = actionBarTitle

        // TODO:
        val results = quizResults
//        val results =  db.resultDao().readAllData().value.orEmpty()
        val xAxis = listOf<QuizType>()
//        val xAxis = db.progressDao().readAllData().value.orEmpty()

        Log.d("cos1", results.toString())
        Log.d("cos2", xAxis.toString())

        val barChart: BarChart = findViewById(R.id.bar_chart)
        // TODO: zmienić array list na wszystkie rekrdy z db
        val ratioHashMap: LinkedHashMap<String, Float> = GraphDataPreparer.prepareIntervalsMap(
            results, ArrayList(MusicTerminology.intervals.keys)
        )
        val barChartManager = BarChartManager(barChart)

        val xAxisLabels: ArrayList<String> = ArrayList(ratioHashMap.keys)

        barChartManager.setupBarChart()
        barChartManager.setXAxisLabels(xAxisLabels)
        barChartManager.setDataValues(ArrayList(ratioHashMap.values))

    }
}
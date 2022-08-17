package com.example.earsensei.graphs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.earsensei.MusicTerminology
import com.example.earsensei.R
import com.example.earsensei.database.result.Result
import com.example.earsensei.graphdatapreparers.DetailsGraphDataPreparer
import com.github.mikephil.charting.charts.BarChart

class StatsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_details)

        val filter: String = intent.getStringExtra("FILTER") ?: "Extra was null"
        actionBar?.setTitle(filter)
        supportActionBar?.setTitle(filter)

        //todo zmienić arraylist na all z db
        val filteredIntervalsData: ArrayList<Result> = ArrayList(arrayListOf())

        val graphDataPreparer: DetailsGraphDataPreparer =
            DetailsGraphDataPreparer(
                filteredIntervalsData, ArrayList(
                    MusicTerminology.intervals.keys
                ), { result: Result -> result.correctAnswer }, filter
            )
        val barChart: BarChart = findViewById(R.id.bar_chart)
        val ratioHashMap: LinkedHashMap<String, Float> = graphDataPreparer.prepareXYHashMap()
        val barChartManager: BarChartManager = BarChartManager(barChart)

        val xAxisLabels: ArrayList<String> = ArrayList(ratioHashMap.keys)

        barChartManager.setupBarChart()
        barChartManager.setXAxisLabels(xAxisLabels)
        barChartManager.setDataValues(ArrayList(ratioHashMap.values))
    }
}
package com.example.earsensei.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.earsensei.*
import com.example.earsensei.graphdatapreparers.GraphDataPreparer
import com.example.earsensei.dbmodels.QuizModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class StatsGeneralActivity : AppCompatActivity() {

    val earSenseiDBHelper : EarSenseiDBHelper = EarSenseiDBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_general)

        val actionBarTitle : String? = intent.getStringExtra("TITLE")
        actionBar?.setTitle(actionBarTitle)
        supportActionBar?.setTitle(actionBarTitle)

        val graphDataPreparer : GraphDataPreparer = GraphDataPreparer(earSenseiDBHelper.readAllIntervalsData(), ArrayList(
            Note.intervals.keys), { quizModel : QuizModel -> quizModel.correctAnswer})
        val barChart : BarChart = findViewById(R.id.bar_chart)
        val ratioHashMap : LinkedHashMap<String, Float> = graphDataPreparer.prepareXYHashMap()
        val barChartManager : BarChartManager = BarChartManager(barChart)

        var orderHashMap : LinkedHashMap<Float, String> = linkedMapOf()
        val xAxisLabels : ArrayList<String> = ArrayList(ratioHashMap.keys)
        orderHashMap = barChartManager.setOrderdHashMap(xAxisLabels)

        barChartManager.setupBarChart()
        barChartManager.setXAxisLabels(xAxisLabels)
        barChartManager.setDataValues(ArrayList(ratioHashMap.values))

        barChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener{
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                val intent : Intent = Intent(this@StatsGeneralActivity, StatsDetailsActivity::class.java)
                val barXPosition : Float? = e?.x
                val intervalName : String = orderHashMap[barXPosition] ?: "error"
                intent.putExtra("FILTER", intervalName)
                startActivity(intent)
            }
            override fun onNothingSelected() {
            }
        })

        

    }
}
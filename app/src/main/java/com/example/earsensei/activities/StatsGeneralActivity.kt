package com.example.earsensei.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.earsensei.*
import com.example.earsensei.activities.graphpreparers.GraphPreparer
import com.example.earsensei.dbmodels.QuizRecordModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarEntry
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

        val plotDataPreparer : GraphPreparer = GraphPreparer(earSenseiDBHelper.readAllIntervalsData(), ArrayList(
            Note.intervals.keys), {quizRecordModel : QuizRecordModel -> quizRecordModel.correctAnswer})
        val barChart : BarChart = findViewById(R.id.bar_chart)
        val ratioHashMap : LinkedHashMap<String, Float> = plotDataPreparer.prepareHashMap()
        var orderHashMap : LinkedHashMap<Float, String> = linkedMapOf()
        val barChartManager : BarChartManager = BarChartManager(barChart)





        val description : Description = barChart.description
        description.isEnabled = false

        val legend : Legend = barChart.legend
        legend.isEnabled = false





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
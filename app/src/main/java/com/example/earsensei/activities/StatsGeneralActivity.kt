package com.example.earsensei.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.earsensei.*
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class StatsGeneralActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_stats_general)

        val actionBarTitle : String? = intent.getStringExtra("TITLE")
        actionBar?.setTitle(actionBarTitle)
        supportActionBar?.setTitle(actionBarTitle)

        val plotDataPreparer : TestGraphPreparer = TestGraphPreparer(earSenseiDBHelper.readAllTestData(), ArrayList(
            Note.intervals.keys))
        val barChart : BarChart = findViewById(R.id.bar_chart)
        val dataValues1 : ArrayList<BarEntry> = arrayListOf()
        val ratioHashMap : LinkedHashMap<String, Float> = plotDataPreparer.prepareHashMap()
        val orderHashMap : LinkedHashMap<Float, String> = linkedMapOf()
        val barChartManager : BarChartManager = BarChartManager(barChart)


        var iterator : Float = 0F
        ratioHashMap.forEach(){
            dataValues1.add(BarEntry(iterator, it.value))
            orderHashMap.put(iterator, it.key)
            iterator+= barDistance
        }

        val description : Description = barChart.description
        description.isEnabled = false

        val legend : Legend = barChart.legend
        legend.isEnabled = false


        barChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener{
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                val intent : Intent = Intent(this@StatsGeneralActivity, StatsDetailsActivity::class.java)
                val intervalName : String = orderHashMap[e?.x] ?: "error"
                intent.putExtra("FILTER", intervalName)
                startActivity(intent)
            }

            override fun onNothingSelected() {

            }
        })


        val xAxisLabels : ArrayList<String> = ArrayList(ratioHashMap.keys)

        barChartManager.setupBarChart()
        barChartManager.setXAxisLabels(xAxisLabels)
        barChartManager.setDataValues(dataValues1)

    }
}
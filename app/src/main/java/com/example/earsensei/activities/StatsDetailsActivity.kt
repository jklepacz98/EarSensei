package com.example.earsensei.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.earsensei.EarSenseiDBHelper
import com.example.earsensei.Note
import com.example.earsensei.R
import com.example.earsensei.TestDetailsGraphPreparer
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

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

        val filter : String = intent.getStringExtra("NAME") ?: "major 9th"


//        val noteNames : ArrayList<String> = arrayListOf()
//        Note.notePlayers.forEach {
//            noteNames.add(it.name)
//        }
//
//        val plotDataPreparer : TestDetailsGraphPreparer = TestDetailsGraphPreparer(earSenseiDBHelper.readAllTestData(), ArrayList(noteNames))

        //val filter: String = "Major 9th"

        val plotDataPreparer : TestDetailsGraphPreparer = TestDetailsGraphPreparer(earSenseiDBHelper.readAllTestData(), ArrayList(
            Note.notes.keys), filter)

        val barChart : BarChart = findViewById(R.id.bar_chart)

        val dataValues1 : ArrayList<BarEntry> = arrayListOf()



        val ratioHashMap : LinkedHashMap<String, Float> = plotDataPreparer.prepareHashMap()



        var iterator : Float = 0F
        ratioHashMap.forEach(){
            dataValues1.add(BarEntry(iterator, it.value))
            iterator+= StatsGeneralActivity.barDistance
        }

        val description : Description = barChart.description
        description.isEnabled = false

        val legend : Legend = barChart.legend
        legend.isEnabled = false

        val xAxis : XAxis = barChart.xAxis
        xAxis.labelCount = StatsGeneralActivity.maxVisibleXAxisLabels
        xAxis.granularity = StatsGeneralActivity.barDistance
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textSize = StatsGeneralActivity.xAxisLabelTextSize

        val yTopAxis : YAxis = barChart.axisLeft
        yTopAxis.axisMinimum = StatsGeneralActivity.yAxisMinimum
        yTopAxis.axisMaximum = StatsGeneralActivity.yAxisMaximum
        //yTopAxis.setDrawLabels(false)


        val yBottomAxis : YAxis = barChart.axisRight
        yBottomAxis.axisMinimum = StatsGeneralActivity.yAxisMinimum
        yBottomAxis.axisMaximum = StatsGeneralActivity.yAxisMaximum
        yBottomAxis.setDrawGridLines(false)
        yBottomAxis.setDrawLabels(false)

        val xAxisLabels : ArrayList<String> = ArrayList(ratioHashMap.keys)
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)

        val barDataSet1 : BarDataSet = BarDataSet(dataValues1, "DataSet 1")
        barDataSet1.setColor(Color.GREEN)

        val barData : BarData = BarData(barDataSet1)
        barData.barWidth = StatsGeneralActivity.barWidth
        barData.setValueTextSize(StatsGeneralActivity.barTextSize)

        barChart.data = barData
        barChart.setFitBars(true)
        barChart.isScaleXEnabled = false
        barChart.setExtraOffsets(0F, 0F, StatsGeneralActivity.rightOffset, 0F)
        barChart.animateY(StatsGeneralActivity.animationLengthInMs, Easing.EaseInOutQuad)
        barChart.invalidate()
    }
}
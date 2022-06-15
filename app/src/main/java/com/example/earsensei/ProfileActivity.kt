package com.example.earsensei

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

class ProfileActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_profile)

        val plotDataPreparer : PlotDataPreparer = PlotDataPreparer(earSenseiDBHelper.readAllData(), ArrayList(Note.intervals.keys))

        val barChart : BarChart = findViewById(R.id.bar_chart)

        val dataValues1 : ArrayList<BarEntry> = arrayListOf()

        val ratioHashMap : LinkedHashMap<String, Float> = plotDataPreparer.ratioHashMap()


        var iterator : Float = 0F
        ratioHashMap.forEach(){
            dataValues1.add(BarEntry(iterator, it.value))
            iterator+= barDistance
        }

        val description : Description = barChart.description
        description.isEnabled = false

        val legend : Legend = barChart.legend
        legend.isEnabled = false



        val xAxis : XAxis = barChart.xAxis
        xAxis.labelCount = maxVisibleXAxisLabels
        xAxis.granularity = barDistance
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textSize = xAxisLabelTextSize

        val yTopAxis : YAxis = barChart.axisLeft
        yTopAxis.axisMinimum = yAxisMinimum
        yTopAxis.axisMaximum = yAxisMaximum
        //yTopAxis.setDrawLabels(false)


        val yBottomAxis : YAxis = barChart.axisRight
        yBottomAxis.axisMinimum = yAxisMinimum
        yBottomAxis.axisMaximum = yAxisMaximum
        yBottomAxis.setDrawGridLines(false)
        yBottomAxis.setDrawLabels(false)

        val xAxisLabels : ArrayList<String> = ArrayList(ratioHashMap.keys)
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)

        val barDataSet1 : BarDataSet = BarDataSet(dataValues1, "DataSet 1")
        barDataSet1.setColor(Color.GREEN)

        val barData : BarData = BarData(barDataSet1)
        barData.barWidth = barWidth
        barData.setValueTextSize(barTextSize)

        barChart.data = barData
        barChart.setFitBars(true)
        barChart.isScaleXEnabled = false
        barChart.isDoubleTapToZoomEnabled = false
        barChart.setExtraOffsets(0F, 0F, rightOffset, 0F)
        barChart.animateY(animationLengthInMs, Easing.EaseInOutQuad)
        barChart.invalidate()
    }
}
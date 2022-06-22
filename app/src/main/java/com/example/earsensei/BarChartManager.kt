package com.example.earsensei

import android.graphics.Color
import com.example.earsensei.activities.StatsGeneralActivity
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

class BarChartManager(val barChart: BarChart) {
    fun setupBarChart(){
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



        barChart.setFitBars(true)
        barChart.isScaleXEnabled = false
        barChart.setExtraOffsets(0F, 0F, StatsGeneralActivity.rightOffset, 0F)
        barChart.animateY(StatsGeneralActivity.animationLengthInMs, Easing.EaseInOutQuad)

        barChart.invalidate()
    }

    fun setXAxisLabels(xAxisLabels : ArrayList<String>){
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        barChart.invalidate()
    }

    fun setDataValues(dataValues : ArrayList<BarEntry>){
        val barDataSet1 : BarDataSet = BarDataSet(dataValues, "DataSet 1")
        barDataSet1.setColor(Color.GREEN)
        val barData : BarData = BarData(barDataSet1)
        barData.barWidth = StatsGeneralActivity.barWidth
        barData.setValueTextSize(StatsGeneralActivity.barTextSize)
        barChart.data = barData
    }
}
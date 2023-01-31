package com.example.earsensei.graphs

import android.graphics.Color
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

class BarChartManager(private val barChart: BarChart) {

    private val barDistance: Float = 1F
    private val barWidth: Float = 0.9F
    private val maxVisibleXAxisLabels: Int = 20
    private val animationLengthInMs: Int = 750
    private val yAxisMinimum: Float = 0F
    private val yAxisMaximum: Float = 1F + 0.001F //0.001F addition is to show last grid line
    private val rightOffset: Float = 32F
    private val xAxisLabelTextSize: Float = 16F
    private val barTextSize: Float = 12F

//    var barDistance: Float = barChartModel.barDistance
//    var barWidth: Float = barChartModel.barWidth
//    var maxVisibleXAxisLabels: Int = barChartModel.maxVisibleXAxisLabels
//    var animationLengthInMs: Int = barChartModel.animationLengthInMs
//    var yAxisMinimum: Float = barChartModel.yAxisMinimum
//    var yAxisMaximum: Float = barChartModel.yAxisMaximum
//    var rightOffset: Float = barChartModel.rightOffset
//    var xAxisLabelTextSize: Float = barChartModel.xAxisLabelTextSize
//    var barTextSize: Float = barChartModel.barTextSize

    fun setupBarChart() {
        setupDescription()
        setupLegend()
        setupXTopAxis()
        setupYTopAxis()
        setupYBottomAxis()
        barChart.setFitBars(true)
        barChart.isScaleXEnabled = false
        barChart.setExtraOffsets(0F, 0F, rightOffset, 0F)
        barChart.animateY(animationLengthInMs, Easing.EaseInOutQuad)
        barChart.invalidate()
    }

    fun setupDescription() {
        val description: Description = barChart.description
        description.isEnabled = false
    }

    fun setupLegend() {
        val legend: Legend = barChart.legend
        legend.isEnabled = false
    }

    fun setupXTopAxis() {
        val xAxis: XAxis = barChart.xAxis
        xAxis.labelCount = maxVisibleXAxisLabels
        xAxis.granularity = barDistance
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textSize = xAxisLabelTextSize
    }

    fun setupYTopAxis() {
        val yTopAxis: YAxis = barChart.axisLeft
        yTopAxis.axisMinimum = yAxisMinimum
        yTopAxis.axisMaximum = yAxisMaximum
        //yTopAxis.setDrawLabels(false)
    }

    fun setupYBottomAxis() {
        val yBottomAxis: YAxis = barChart.axisRight
        yBottomAxis.axisMinimum = yAxisMinimum
        yBottomAxis.axisMaximum = yAxisMaximum
        yBottomAxis.setDrawGridLines(false)
        yBottomAxis.setDrawLabels(false)
    }

    fun setXAxisLabels(xAxisLabels: List<String>) {
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        barChart.invalidate()
    }

    fun setDataValues(xValues: List<Float>) {
        val dataValues: List<BarEntry> = createBarEntries(xValues)
        val barDataSet1 = BarDataSet(dataValues, "DataSet 1")
        barDataSet1.color = Color.GREEN
        val barData = BarData(barDataSet1)
        barData.barWidth = barWidth
        barData.setValueTextSize(barTextSize)
        barChart.data = barData
    }

    private fun createBarEntries(xValues: List<Float>): List<BarEntry> {
        var iterator = 0F
        val dataValues: ArrayList<BarEntry> = arrayListOf()
        xValues.forEach() {
            if (!it.isNaN()) {
                dataValues.add(BarEntry(iterator, it))
                iterator += barDistance
            }
        }
        return dataValues
    }

    private companion object {
        const val ZERO = 0F
    }
}
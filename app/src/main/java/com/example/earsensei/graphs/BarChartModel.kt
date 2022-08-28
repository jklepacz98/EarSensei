package com.example.earsensei.graphs

data class BarChartModel(
    val barDistance: Float = 1F,
    val barWidth: Float = 0.9F,
    val maxVisibleXAxisLabels: Int = 20,
    val animationLengthInMs: Int = 750,
    val yAxisMinimum: Float = 0F,
    val yAxisMaximum: Float = 1F + 0.001F, //0.001F addition is to show last grid line
    val rightOffset: Float = 32F,
    val xAxisLabelTextSize: Float = 16F,
    val barTextSize: Float = 12F,
    val xData: String,
    val yData: Float
) {
}
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
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val plotDataPreparer : PlotDataPreparer = PlotDataPreparer(earSenseiDBHelper.readAllData(), ArrayList(Note.intervals.keys))

        val barChart : BarChart = findViewById(R.id.bar_chart)

        val dataValues1 : ArrayList<BarEntry> = arrayListOf()

        val ratioHashMap : HashMap<String, Float> = plotDataPreparer.ratioHashMap()

        //barChart.setTouchEnabled(false)


        //barChart.setDrawValueAboveBar(false)
        barChart.setDrawBarShadow(true)


        val description : Description = barChart.description
        description.isEnabled = false

        val legend : Legend = barChart.legend
        legend.isEnabled = false

        val xAxis : XAxis = barChart.xAxis
        val yTopAxis : YAxis = barChart.axisLeft
        val yBottomAxis : YAxis = barChart.axisRight

        yBottomAxis.isEnabled = false


        yTopAxis.axisMinimum = 0F
        yTopAxis.axisMaximum = 1F
        yTopAxis.spaceTop = 100F


        xAxis.labelCount = maxVisibleXAxisLabels
        xAxis.granularity = barDistance
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        //xAxis.axisMinimum = 10F
        //xAxis.axisMaximum = 150F

        var iterator : Float = 0F
        ratioHashMap.forEach(){
            if(it.value != Float.NaN) {
                dataValues1.add(BarEntry(iterator, it.value))
                Log.d("lol", it.value.toString())
            }
            else{
                dataValues1.add(BarEntry(iterator, 0F))
            }
            iterator+= barDistance
        }




        val barDataSet1 : BarDataSet = BarDataSet(dataValues1, "DataSet 1")
        barDataSet1.setColor(Color.GREEN)

        val barData : BarData = BarData(barDataSet1)
        barData.barWidth = barWidth


        val xAxisLabels : ArrayList<String> = ArrayList(ratioHashMap.keys)
//        val xAxisLabels : ArrayList<String> = arrayListOf("1", "cps", "fun")
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)



        barChart.data = barData
        //TODO
        //barChart.xAxis.axisMinimum = -barData.barWidth
        //barChart.xAxis.axisMaximum = ratioHashMap.size as Float
        barChart.setFitBars(true)
        barChart.animateY(750, Easing.EaseInOutQuad)
        barChart.invalidate()
    }
}
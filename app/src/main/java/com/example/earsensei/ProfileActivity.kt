package com.example.earsensei

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class ProfileActivity : AppCompatActivity() {

    val earSenseiDBHelper : EarSenseiDBHelper = EarSenseiDBHelper(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val plotDataPreparer : PlotDataPreparer = PlotDataPreparer(earSenseiDBHelper.readAllData(), ArrayList(Note.intervals.keys))

        val barChart : BarChart = findViewById(R.id.bar_chart)

        val dataValues1 : ArrayList<BarEntry> = arrayListOf()

        val ratioHashMap : HashMap<String, Float> = plotDataPreparer.ratioHashMap()

        barChart.setTouchEnabled(false)

        var iterator : Float = 0F
        ratioHashMap.forEach(){
            dataValues1.add(BarEntry(iterator, it.value))
            iterator+=1F
        }

        Log.d("TAG", ratioHashMap.keys.toString())


        val barDataSet1 : BarDataSet = BarDataSet(dataValues1, "DataSet 1")
        barDataSet1.setColor(Color.GREEN)

        val barData : BarData = BarData(barDataSet1)
        barData.barWidth = 0.5F


        val xAxisLabels : ArrayList<String> = ArrayList(ratioHashMap.keys)
//        val xAxisLabels : ArrayList<String> = arrayListOf("1", "cps", "fun")
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)

        barChart.data = barData
        //TODO
        //barChart.xAxis.axisMinimum = -barData.barWidth
        //barChart.xAxis.axisMaximum = ratioHashMap.size as Float
        barChart.setFitBars(true)
        barChart.invalidate()
    }
}
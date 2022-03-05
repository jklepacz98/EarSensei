package com.example.earsensei

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val barChart : BarChart = findViewById(R.id.bar_chart)

        val dataValues1 : ArrayList<BarEntry> = arrayListOf()
        dataValues1.add(BarEntry(0.0f, 33.0f))
        dataValues1.add(BarEntry(1.0f, 99.3f))
        dataValues1.add(BarEntry(2.0f, 19.3f))

        val barDataSet1 : BarDataSet = BarDataSet(dataValues1, "DataSet 1")
        barDataSet1.setColor(Color.GREEN)

        val barData : BarData = BarData()
        barData.addDataSet(barDataSet1)

        barChart.data = barData
        barChart.invalidate()
    }
}
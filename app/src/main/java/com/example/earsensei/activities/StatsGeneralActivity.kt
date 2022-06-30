package com.example.earsensei.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.earsensei.*
import com.example.earsensei.graphdatapreparers.GraphDataPreparer
import com.example.earsensei.dbmodels.QuizModel
import com.github.mikephil.charting.charts.BarChart
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

        val barChart : BarChart = findViewById(R.id.bar_chart)
        var ratioHashMap : LinkedHashMap<String, Float> = GraphDataPreparer.prepareIntervalsHashMap(earSenseiDBHelper.readAllIntervalsData(), ArrayList(
            MusicTerminology.intervals.keys), { quizModel : QuizModel -> quizModel.correctAnswer})
        val barChartManager : BarChartManager = BarChartManager(barChart)

        var orderHashMap : LinkedHashMap<Float, String> = linkedMapOf()
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

        val spinner : Spinner = findViewById(R.id.date_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.quantity_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val chosenOption : String = adapterView?.getItemAtPosition(position).toString()
                //TODO
                if (chosenOption == "All"){
                    ratioHashMap = GraphDataPreparer.prepareIntervalsHashMap(earSenseiDBHelper.readAllIntervalsData(), ArrayList(
                        MusicTerminology.intervals.keys), { quizModel : QuizModel -> quizModel.correctAnswer})

                } else{
                    Toast.makeText(this@StatsGeneralActivity, chosenOption.toInt().toString(), Toast.LENGTH_SHORT).show()
                    ratioHashMap = GraphDataPreparer.prepareIntervalsHashMap(chosenOption.toInt() ,earSenseiDBHelper.readAllIntervalsData(), ArrayList(
                        MusicTerminology.intervals.keys), { quizModel : QuizModel -> quizModel.correctAnswer})
                }
                orderHashMap = barChartManager.setOrderdHashMap(xAxisLabels)
                barChartManager.setupBarChart()
                barChartManager.setXAxisLabels(xAxisLabels)
                barChartManager.setDataValues(ArrayList(ratioHashMap.values))
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

    }


}
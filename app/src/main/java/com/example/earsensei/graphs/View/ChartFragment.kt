package com.example.earsensei.graphs.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.earsensei.database.quizResult.QuizResultTestDataset
import com.example.earsensei.databinding.FragmentChartBinding
import com.example.earsensei.graphs.BarChartManager
import com.example.earsensei.graphs.ViewModel.ChartViewModel

class ChartFragment : Fragment() {
    private lateinit var binding: FragmentChartBinding

    private val quizResults = QuizResultTestDataset.generateQuizResults()
    private lateinit var barChartManager: BarChartManager

    val viewModel: ChartViewModel by lazy {
        ViewModelProvider(this@ChartFragment).get(ChartViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChartBinding.inflate(layoutInflater)
        barChartManager = BarChartManager(binding.barChart)
        setupChartDataObserver()
        barChartManager.setupBarChart()
        return binding.root
    }

    private fun setupChartDataObserver() {
        viewModel.chartData.observe(viewLifecycleOwner) {
            val translations = it.keys.map { resources.getText(it).toString() }
            barChartManager.setXAxisLabels(translations)
            barChartManager.setDataValues(it.values.toList())
        }
    }

}
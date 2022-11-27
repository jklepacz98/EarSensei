package com.example.earsensei.graphs.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.earsensei.databinding.FragmentChartBinding
import com.example.earsensei.graphs.BarChartManager
import com.example.earsensei.graphs.ViewModel.GraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GraphFragment : Fragment() {
    private lateinit var binding: FragmentChartBinding

    private lateinit var barChartManager: BarChartManager

    private val args: GraphFragmentArgs by navArgs()

    private val viewModel by viewModel<GraphViewModel> {
        val type = args.quizType
        parametersOf(type)
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
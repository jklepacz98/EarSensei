package com.example.earsensei.start

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.earsensei.R
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.database.quizResult.QuizResultTestDataset
import com.example.earsensei.databinding.FragmentStartBinding
import kotlinx.coroutines.runBlocking

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater)
        setupButtons()
        return binding.root
    }

    private fun setupButtons() {
        binding.buttonIntervals.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_intervalsFragment)
        }
        binding.buttonStats.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_statsFragment)
        }
        binding.buttonAddDataToDatabase.setOnClickListener {
            val results = QuizResultTestDataset.generateQuizResults()
            val db = EarSenseiDatabase.getDataBase(requireContext())
            runBlocking {

                db.resultDao().insert(*results.toTypedArray())
                    .also { Log.d("cosek", it.toString()) }
            }
        }
    }
}
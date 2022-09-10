package com.example.earsensei.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.database.quizResult.QuizResultTestDataset
import com.example.earsensei.databinding.FragmentStartBinding
import com.example.earsensei.utils.navigate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            this@StartFragment.navigate(StartFragmentDirections.actionStartFragmentToIntervalsFragment())
        }
        binding.buttonChords.setOnClickListener {
            this@StartFragment.navigate(StartFragmentDirections.actionStartFragmentToChordsQuizFragment())
        }
        binding.buttonStats.setOnClickListener {
            this@StartFragment.navigate(StartFragmentDirections.actionStartFragmentToStatsFragment())
        }
        binding.buttonAddDataToDatabase.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val results = QuizResultTestDataset.generateQuizResults()
                val progressList = QuizResultTestDataset.generateProgress()
                val db = EarSenseiDatabase.getDataBase(requireContext())
                db.resultDao().insert(*results.toTypedArray())
                db.unlockedquestionDao().insert(*progressList.toTypedArray())
            }
        }
        binding.buttonDeleteDataFromDatabase.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val db = EarSenseiDatabase.getDataBase(requireContext())
                db.resultDao().deleteAll()
                db.unlockedquestionDao().deleteAll()
            }
        }
    }
}
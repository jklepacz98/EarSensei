package com.example.earsensei.activities.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.earsensei.R

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val progressBar : ProgressBar = findViewById(R.id.progress_bar)
        progressBar.max = 20
        progressBar.progress = 0

        val intervalsQuizFragment : IntervalsQuizFragment = IntervalsQuizFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, intervalsQuizFragment).commit()
        sendBundleOfIntArray(intervalsQuizFragment, arrayListOf(1, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1))

        val nextButton : Button = findViewById(R.id.next_button)

        nextButton.setOnClickListener(){
            val intervalsQuizFragment2 : IntervalsQuizFragment = IntervalsQuizFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, intervalsQuizFragment2).commit()
            if(progressBar.progress < progressBar.max){
                progressBar.progress++
            }else{
                //todo
                progressBar.progress = 0
            }
            sendBundleOfIntArray(intervalsQuizFragment2, arrayListOf(1, 10, 24, 5))
        }
    }

    fun sendBundleOfIntArray(fragment : Fragment, intArrayList: ArrayList<Int>){
        val bundle : Bundle = Bundle()
        bundle.putIntegerArrayList("NOTES", intArrayList)
        fragment.arguments = bundle
    }


}
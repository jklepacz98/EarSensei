package com.example.earsensei.activities.graphpreparers

import com.example.earsensei.dbmodels.TestModel

class GivenTestBaseNoteGraphPreparer(val testModels : ArrayList<TestModel>, val plotX : ArrayList<String>, val filter: String) :
    GraphPreparer {

    override fun prepareHashMap() : LinkedHashMap<String, Float> {
        val ratio : LinkedHashMap<String, Float> = linkedMapOf()
        plotX.forEach(){

            ratio.put(it, countCorrectAnswerRatio(it))

        }
        return ratio
    }

    private fun countCorrectAnswerRatio(answer : String) : Float{
        var correctAnswers : Float = 0F
        var allAnswers : Float = 0F
        filterTestModels().forEach(){
            if(answer == it.baseNote){
                allAnswers++
                if(it.correctAnswer == it.userAnswer){
                    correctAnswers++
                }
            }
        }
        var correctAnswerRatio : Float = correctAnswers as Float / allAnswers as Float
        if(correctAnswerRatio.isNaN()) return 0F
        return correctAnswerRatio
    }

    private fun filterTestModels() : ArrayList<TestModel>{
        val filteredTestModels : ArrayList<TestModel> = arrayListOf()
        testModels.forEach(){
            if (it.correctAnswer == filter){
                filteredTestModels.add(it)
            }
        }
        return filteredTestModels
    }

}
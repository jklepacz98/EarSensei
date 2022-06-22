package com.example.earsensei.activities.graphpreparers

import com.example.earsensei.dbmodels.TestModel

class TestGraphPreparer(val testModels : ArrayList<TestModel>, val plotX : ArrayList<String>) :
    GraphPreparer {

    override fun prepareHashMap() : LinkedHashMap<String, Float> {
        val ratio : LinkedHashMap<String, Float> = linkedMapOf()
        plotX.forEach(){

            ratio.put(it, countCorrectAnswerRatio(it))

        }
        return ratio
    }

    fun countCorrectAnswerRatio(answer : String) : Float{
        var correctAnswers : Float = 0F
        var allAnswers : Float = 0F
        testModels.forEach(){
            if(answer == it.correctAnswer){
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

}
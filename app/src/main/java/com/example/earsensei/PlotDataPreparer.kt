package com.example.earsensei

class PlotDataPreparer(val testModels : ArrayList<TestModel>, val plotX : ArrayList<String>) {

    fun ratioHashMap() : HashMap<String, Float> {
        val ratio : HashMap<String, Float> = hashMapOf()
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
        return correctAnswers as Float / allAnswers as Float
    }

}
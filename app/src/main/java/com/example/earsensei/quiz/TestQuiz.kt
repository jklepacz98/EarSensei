package com.example.earsensei.quiz

class TestQuiz(val answers : Map<String, Int>) : Quizable {
    private lateinit var correctAnswer : String
    override fun createQuiz() {
        correctAnswer = answers.keys.random()
    }

    override fun getAnswers(): Array<String> {
        return answers.keys.toTypedArray()
    }

    override fun checkAnswer(answer: String): Boolean {
        return answer == correctAnswer
    }
}
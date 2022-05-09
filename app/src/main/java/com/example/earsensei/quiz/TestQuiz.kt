package com.example.earsensei.quiz

class TestQuiz(val answers : Map<String, Int>) : Quizable {
    private val correctAnswer = answers.keys.random()

    override fun getAnswers(): Array<String> {
        return answers.keys.toTypedArray()
    }

    override fun getCorrectAnswer(): String {
        return correctAnswer
    }

    override fun checkAnswer(answer: String): Boolean {
        return answer == correctAnswer
    }
}
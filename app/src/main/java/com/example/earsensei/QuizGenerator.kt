package com.example.earsensei

import com.example.earsensei.database.Answer
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.database.Quiz

class QuizGenerator(val db: EarSenseiDatabase) {
    var numberOfNormal: Int = 4
    var numberOfAdjusted: Int = 4
    val progressManager = ProgressManager(db)

    fun generateQuizes(type: String): List<Quiz> {
        val quizes = mutableListOf<Quiz>()
        val unlockedQuestions =
            db.unlockedquestionDao().getAllData().sortedBy { INTERVALS.valueOf(it.question).order }
        val worst = progressManager.getWorstRecord(type)
        val mistake = progressManager.getMostCommonMistake(type, worst.key)
        for (i in 0..(numberOfNormal - 1)) {
            val quiz = generateQuiz(unlockedQuestions.map { it.question })
            quizes.add(quiz)
        }
        for (i in 0..(numberOfAdjusted - 1)) {
            val quiz = generateQuiz(listOf(worst.key, mistake.key))
            quizes.add(quiz)
        }
        return quizes
    }


    private fun generateQuiz(answers: List<String>): Quiz {
        val answers = generateAnswers(answers)
        val correctAnswer = answers.filter { it.isCorrect == true }.first()
        //todo zmienić nazwę
        val range = INTERVALS.valueOf(correctAnswer.name).getRange()
        val baseNote = range.keys.random()
        return Quiz(baseNote, correctAnswer, answers)
    }

    private fun generateAnswers(answerPool: List<String>): List<Answer> {
        val answers = mutableListOf<Answer>()
        answerPool.forEach {
            val interval = INTERVALS.valueOf(it)
            val answer = Answer(
                interval.name,
                interval.translation,
                false
            )
            answers.add(answer)
        }
        answers.random().isCorrect = true
        return answers
    }
}
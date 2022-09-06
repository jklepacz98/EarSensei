package com.example.earsensei

import com.example.earsensei.database.Answer
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.database.Quiz

class QuizGenerator(val db: EarSenseiDatabase, val musicable: Musicable) {
    var numberOfNormal: Int = 4
    var numberOfAdjusted: Int = 4
    val type = musicable.getType()

    val progressManager = ProgressManager(db)

    fun generateQuizes(type: String): List<Quiz> {
        val progression = db.progressionDao().getAllData()
        val worst = progressManager.getWorstRecord(type)
        val mistake = progressManager.getMostCommonMistake(type, worst.key)
        val quizes = mutableListOf<Quiz>()
        for (i in 0..(numberOfNormal - 1)) {
            val quiz = generateQuiz(progression.map { it.question })
            quizes.add(quiz)
        }
        for (i in 0..(numberOfAdjusted - 1)) {
            val quiz = generateQuiz(listOf(worst.key, mistake.key))
            quizes.add(quiz)
        }
        return quizes
    }


    private fun generateQuiz(answers: List<String>): Quiz {
        val answers = generateNormalAnswers(answers)
        val correctAnswer = answers.filter { it.isCorrect == true }.first()
        //todo
        val limit = INTERVALS.valueOf(correctAnswer.name).halfSteps
        val baseNote =
            NOTES_WITH_OCTAVE.filter { it.value < NOTES_WITH_OCTAVE.size - limit }.keys.random()
        return Quiz(baseNote, correctAnswer, answers)
    }

    private fun generateNormalAnswers(progression: List<String>): List<Answer> {

        val answers = mutableListOf<Answer>()
        progression.forEach {
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
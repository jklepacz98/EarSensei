package com.example.earsensei

import com.example.earsensei.database.Answer
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.database.Quiz

class QuizGenerator(val db: EarSenseiDatabase) {
    var numberOfNormal: Int = 4
    var numberOfAdjusted: Int = 4
    val hardestQuestionsFinder = HardestQuestionsFinder(db)

    fun generateQuizes(musicTerminology: MusicTerminology): List<Quiz> {
        val type = musicTerminology.type
        val quizes = mutableListOf<Quiz>()
        val unlockedQuestions =
            db.unlockedquestionDao().getByType(type)
                .sortedBy { musicTerminology.musicMap.get(it.question)!!.order }
        val worst = hardestQuestionsFinder.getWorstRecord(type)
        if (worst != null) {
            val mistake = hardestQuestionsFinder.getMostCommonMistake(type, worst.key)
            if (mistake != null) {
                for (i in 0..(numberOfNormal - 1)) {
                    val quiz = generateQuiz(unlockedQuestions.map { it.question }, musicTerminology)
                    quizes.add(quiz)
                }
                for (i in 0..(numberOfAdjusted - 1)) {
                    val quiz = generateQuiz(listOf(worst.key, mistake.key), musicTerminology)
                    quizes.add(quiz)
                }
            } else {
                for (i in 0..(numberOfNormal + numberOfAdjusted - 1)) {
                    val quiz = generateQuiz(unlockedQuestions.map { it.question }, musicTerminology)
                    quizes.add(quiz)
                }
            }
        } else {
            for (i in 0..(numberOfNormal + numberOfAdjusted - 1)) {
                val quiz = generateQuiz(unlockedQuestions.map { it.question }, musicTerminology)
                quizes.add(quiz)
            }
        }


        return quizes
    }


    private fun generateQuiz(answers: List<String>, musicTerminology: MusicTerminology): Quiz {
        val answers = generateAnswers(answers, musicTerminology)
        val correctAnswer = answers.filter { it.isCorrect == true }.first()
        val range = musicTerminology.musicMap.get(correctAnswer.name)!!.getRange()
        val baseNote = range.keys.random()
        return Quiz(baseNote, correctAnswer, answers)
    }

    private fun generateAnswers(
        answerPool: List<String>,
        musicTerminology: MusicTerminology
    ): List<Answer> {
        val answers = mutableListOf<Answer>()
        answerPool.forEach {
            val interval = musicTerminology.musicMap.get(it)!!
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
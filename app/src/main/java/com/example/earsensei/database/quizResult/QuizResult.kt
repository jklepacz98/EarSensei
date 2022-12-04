package com.example.earsensei.database.quizResult

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class QuizResult(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val type: String,
    val baseNote: String,
    val correctAnswer: String,
    val userAnswer: String,
    val date: Long = Date().time,
    var isCorrect: Boolean = correctAnswer == userAnswer,
)
//TableInfo{name='QuizResult', columns={date=Column{name='date', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=0, defaultValue='null'}, baseNote=Column{name='baseNote', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, userAnswer=Column{name='userAnswer', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, id=Column{name='id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1, defaultValue='null'}, type=Column{name='type', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, correctAnswer=Column{name='correctAnswer', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, isCorrect=Column{name='isCorrect', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}}, foreignKeys=[], indices=[]}
//TableInfo{name='QuizResult', columns={date=Column{name='date', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}, baseNote=Column{name='baseNote', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, userAnswer=Column{name='userAnswer', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, id=Column{name='id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1, defaultValue='null'}, type=Column{name='type', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, correctAnswer=Column{name='correctAnswer', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, isCorrect=Column{name='isCorrect', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}}, foreignKeys=[], indices=[]}
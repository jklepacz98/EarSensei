package com.example.earsensei.database.result

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Result(
    @PrimaryKey val id: Int,
    @ColumnInfo var quizType: String,
    @ColumnInfo var baseNote: String,
    @ColumnInfo var correctAnswer: String,
    @ColumnInfo var userAnswer: String,
    @ColumnInfo var date: Int,
)
package com.example.earsensei.database.unlockedQuestion

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(indices = [Index(value = ["question"], unique = true)])
data class UnlockedQuestion(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val type: String,
    val question: String,
    val date: Long = Date().time
)
package com.example.earsensei.database.progression

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Progression(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val type: String,
    val question: String,
    val date: Int,
)
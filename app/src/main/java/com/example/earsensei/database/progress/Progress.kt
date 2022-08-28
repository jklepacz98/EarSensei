package com.example.earsensei.database.progress

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Progress(
    @PrimaryKey
    var id: Int = 0,
    val type: String,
    val question: String,
    val date: Int,
)
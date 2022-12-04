package com.example.earsensei.database.unlockedQuestion

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class UnlockedQuestion(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val type: String,
    val name: String,
    val date: Long? = Date().time,
)
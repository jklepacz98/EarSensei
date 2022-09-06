package com.example.earsensei.database

data class Answer(
    val name: String,
    val stringResourceId: Int,
    //Todo zmienić var na val
    var isCorrect: Boolean,
    var isHighlighted: Boolean = false
)


package com.example.earsensei.intervalsquiz.Model

data class AnswerModel(val name: String, val stringResourceId: Int, var state: Int = NOT_CLICKED) {
    companion object {
        val NOT_CLICKED = 0
        val CLICKED_CORRECT = 1
        val CLICKED_WRONG = 2
    }
}
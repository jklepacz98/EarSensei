package com.example.earsensei.quiz.View

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.earsensei.R
import com.example.earsensei.database.Answer
import com.example.earsensei.databinding.ItemAnswerBinding

class IntervalsQuizViewHolder(
    private val view: View,
    private val clickListener: (Answer) -> Unit,
) : RecyclerView.ViewHolder(view) {
    private val binding = ItemAnswerBinding.bind(view)

    fun bind(answer: Answer) {
        setupBtnAnswerClickListener(answer)
        setBtnAnswerText(answer)
        setBtnAnswerColor(answer)
    }

    private fun setupBtnAnswerClickListener(answer: Answer) {
        binding.button.setOnClickListener {

            clickListener.invoke(answer)
        }
    }

    private fun setBtnAnswerText(answer: Answer) {
        val stringResource = view.resources.getText(answer.stringResourceId)
        binding.button.text = stringResource
    }

    private fun setBtnAnswerColor(answer: Answer) {
        binding.button.run {
            if (answer.isHighlighted) {
                if (answer.isCorrect) {
                    setBackgroundResource(R.drawable.default_round_button_right)
                } else {
                    setBackgroundResource(R.drawable.default_round_button_wrong)
                }
            } else {
                setBackgroundResource(R.drawable.default_round_button)
            }
        }
    }
}
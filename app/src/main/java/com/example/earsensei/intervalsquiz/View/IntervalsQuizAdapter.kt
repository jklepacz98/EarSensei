package com.example.earsensei.intervalsquiz.View

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.earsensei.R
import com.example.earsensei.database.AnswerButtonModel

class IntervalsQuizAdapter(
    var answers: List<AnswerButtonModel>,
    val recyclerViewClickListener: RecyclerViewClickListener
) : RecyclerView.Adapter<IntervalsQuizViewHolder>() {

    var areAnswersHighlighted = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntervalsQuizViewHolder {
        val context: Context = parent.context
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val answerView = inflater.inflate(R.layout.item_answer, parent, false)
        return IntervalsQuizViewHolder(answerView, recyclerViewClickListener)
    }

    override fun onBindViewHolder(holder: IntervalsQuizViewHolder, position: Int) {
        val answer: AnswerButtonModel = answers.get(position)
        holder.button.setText(holder.itemView.resources.getText(answer.stringResourceId))
        //todo
        holder.button.setColor(answer)
        when (areAnswersHighlighted) {
            true -> holder.button.setColor(answer)
        }
    }

    private fun Button.setColor(answer: AnswerButtonModel) {
        answers.forEach {
            when (it.isCorrect) {
                true -> setBackgroundResource(R.drawable.default_round_button_right)
                else -> setBackgroundResource(R.drawable.default_round_button_wrong)
            }
        }

    }

    override fun getItemCount(): Int {
        return answers.size
    }

    fun deleteItem(position: Int) {
        answers.drop(position)
        notifyDataSetChanged()
    }

    fun changeList(newAnswers: List<AnswerButtonModel>) {
        answers = newAnswers
        notifyDataSetChanged()
    }


    interface RecyclerViewClickListener {
        fun onClick(position: Int)
    }
}
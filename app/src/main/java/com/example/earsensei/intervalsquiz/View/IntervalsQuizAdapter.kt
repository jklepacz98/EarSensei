package com.example.earsensei.intervalsquiz.View

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.earsensei.R
import com.example.earsensei.database.Answer

class IntervalsQuizAdapter(
    var answers: List<Answer>,
    val recyclerViewClickListener: RecyclerViewClickListener
) : RecyclerView.Adapter<IntervalsQuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntervalsQuizViewHolder {
        val context: Context = parent.context
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val answerView = inflater.inflate(R.layout.item_answer, parent, false)
        return IntervalsQuizViewHolder(answerView, recyclerViewClickListener)
    }

    override fun onBindViewHolder(holder: IntervalsQuizViewHolder, position: Int) {
        val answer: Answer = answers.get(position)
        holder.button.setText(holder.itemView.resources.getText(answer.stringResourceId))
        if (answer.isHighlighted) holder.button.setColor(answer)
        else holder.button.setBackgroundResource(R.drawable.default_round_button)
    }

    private fun Button.setColor(answer: Answer) {
        if (answer.isCorrect) setBackgroundResource(R.drawable.default_round_button_right)
        else setBackgroundResource(R.drawable.default_round_button_wrong)
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    fun changeList(newAnswers: List<Answer>) {
        answers = newAnswers
        notifyDataSetChanged()
    }

    interface RecyclerViewClickListener {
        fun onClick(position: Int)
    }
}
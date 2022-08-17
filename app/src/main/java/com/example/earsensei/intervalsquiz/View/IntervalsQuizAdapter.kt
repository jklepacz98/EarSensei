package com.example.earsensei.intervalsquiz.View

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
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
        val color = getColor(answer)
        when (answer.state) {
            Answer.NOT_CLICKED -> holder.button.setBackgroundColor(Color.WHITE)
            Answer.CLICKED_CORRECT -> holder.button.setBackgroundColor(Color.GREEN)
            Answer.CLICKED_WRONG -> holder.button.setBackgroundColor(Color.RED)
        }
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    fun deleteItem(position: Int) {
        answers.drop(position)
        notifyDataSetChanged()
    }

    fun changeList(newAnswers: List<Answer>) {
        answers = newAnswers
        notifyDataSetChanged()
    }

    fun getColor(answer: Answer): Int =
        when (answer.state) {
            Answer.NOT_CLICKED -> Color.WHITE
            Answer.CLICKED_CORRECT -> Color.GREEN
            else -> Color.RED
        }


    interface RecyclerViewClickListener {
        fun onClick(position: Int)
    }
}
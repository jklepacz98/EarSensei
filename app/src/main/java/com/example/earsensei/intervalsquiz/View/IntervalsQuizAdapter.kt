package com.example.earsensei.intervalsquiz.View

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earsensei.R
import com.example.earsensei.intervalsquiz.Model.AnswerModel

class IntervalsQuizAdapter(
    var answers: List<AnswerModel>,
    val recyclerViewClickListener: RecyclerViewClickListener
) : RecyclerView.Adapter<IntervalsQuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntervalsQuizViewHolder {
        val context: Context = parent.context
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val answerView = inflater.inflate(R.layout.item_answer, parent, false)
        return IntervalsQuizViewHolder(answerView, recyclerViewClickListener)
    }

    override fun onBindViewHolder(holder: IntervalsQuizViewHolder, position: Int) {
        val answerModel: AnswerModel = answers.get(position)
        holder.button.setText(holder.itemView.resources.getText(answerModel.stringResourceId))
        val color = getColor(answerModel)
        when (answerModel.state) {
            AnswerModel.NOT_CLICKED -> holder.button.setBackgroundColor(Color.WHITE)
            AnswerModel.CLICKED_CORRECT -> holder.button.setBackgroundColor(Color.GREEN)
            AnswerModel.CLICKED_WRONG -> holder.button.setBackgroundColor(Color.RED)
        }
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    fun deleteItem(position: Int) {
        answers.drop(position)
        notifyDataSetChanged()
    }

    fun changeList(newAnswers: List<AnswerModel>) {
        answers = newAnswers
        notifyDataSetChanged()
    }

    fun getColor(answerModel: AnswerModel): Int =
        when (answerModel.state) {
            AnswerModel.NOT_CLICKED -> Color.WHITE
            AnswerModel.CLICKED_CORRECT -> Color.GREEN
            else -> Color.RED
        }


    interface RecyclerViewClickListener {
        fun onClick(position: Int)
    }
}
package com.example.earsensei.intervalsquiz.View

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earsensei.R
import com.example.earsensei.intervalsquiz.Model.AnswerModel

class IntervalsQuizAdapter(
    var answers: ArrayList<AnswerModel>,
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
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    fun deleteItem(position: Int){
        answers.drop(position)
        notifyDataSetChanged()
    }

    fun changeList(newAnswers: ArrayList<AnswerModel>){
        answers = newAnswers
        notifyDataSetChanged()
    }

    interface RecyclerViewClickListener {
        fun onClick(position: Int)
    }
}
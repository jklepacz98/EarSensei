package com.example.earsensei.quiz.View

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.earsensei.R
import com.example.earsensei.database.Answer
import com.example.earsensei.utils.getDiffCallback

class QuizAdapter(private val clickListener: (Answer) -> Unit) :
    RecyclerView.Adapter<IntervalsQuizViewHolder>() {

//    private val diffCallback: DiffUtil.ItemCallback<Answer> =
//        object : DiffUtil.ItemCallback<Answer>() {
//
//            override fun areItemsTheSame(oldItem: Answer, newItem: Answer): Boolean =
//                oldItem === newItem
//
//            override fun areContentsTheSame(oldItem: Answer, newItem: Answer): Boolean =
//                oldItem.isHighlighted == newItem.isHighlighted
//
//        }

//    private val asyncDiffer = AsyncListDiffer(this, diffCallback)

    private val asyncDiffer by lazy {
        val diffCallback = getDiffCallback<Answer>() { oldItem, newItem -> oldItem === newItem }
        AsyncListDiffer(this, diffCallback)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntervalsQuizViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_answer, parent, false)
        return IntervalsQuizViewHolder(view, clickListener)
    }

    override fun getItemCount() = asyncDiffer.currentList.size

    override fun onBindViewHolder(holder: IntervalsQuizViewHolder, position: Int) {
        Log.d("cos2", position.toString())
        holder.bind(asyncDiffer.currentList[position])
    }

    fun updateList(newAnswerItems: List<Answer>) {
        Log.d("cos1", newAnswerItems.toString())
        asyncDiffer.submitList(newAnswerItems)
    }
}
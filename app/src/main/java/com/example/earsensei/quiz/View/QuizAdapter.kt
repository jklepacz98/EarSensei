package com.example.earsensei.quiz.View

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.earsensei.R
import com.example.earsensei.database.Answer

class QuizAdapter(private val clickListener: (Answer) -> Unit) :
    RecyclerView.Adapter<QuizViewHolder>() {

    private val diffCallback: DiffUtil.ItemCallback<Answer> =
        object : DiffUtil.ItemCallback<Answer>() {

            override fun areItemsTheSame(oldItem: Answer, newItem: Answer): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: Answer, newItem: Answer): Boolean =
                oldItem.isHighlighted == newItem.isHighlighted

        }

    private val asyncDiffer = AsyncListDiffer(this, diffCallback)

//    private val asyncDiffer by lazy {
//        val diffCallback = getDiffCallback<Answer>() { oldItem, newItem -> oldItem == newItem }
//        AsyncListDiffer(this, diffCallback)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_answer, parent, false)
        return QuizViewHolder(view, clickListener)
    }

    override fun getItemCount() = asyncDiffer.currentList.size

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.bind(asyncDiffer.currentList[position])
    }

    fun updateList(newAnswerItems: List<Answer>) {
        asyncDiffer.submitList(newAnswerItems)
    }
}
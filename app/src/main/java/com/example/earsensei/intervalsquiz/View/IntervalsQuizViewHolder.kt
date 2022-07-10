package com.example.earsensei.intervalsquiz.View

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.earsensei.R

class IntervalsQuizViewHolder(
    val view: View,
    val recyclerViewClickListener: IntervalsQuizAdapter.RecyclerViewClickListener
) : RecyclerView.ViewHolder(view), View.OnClickListener {

    val button: Button = view.findViewById(R.id.button)

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        recyclerViewClickListener.onClick(adapterPosition)
    }
}
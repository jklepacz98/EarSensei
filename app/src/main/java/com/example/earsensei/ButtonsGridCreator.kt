package com.example.earsensei

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout

object ButtonsGridCreator {
    fun createButtonsGrid(context: Context, viewGroup: ViewGroup, notes : List<String>){
        notes.forEach{
            var button = createButton(context, viewGroup)
            button.setText(it)
            viewGroup.addView(button)
        }
    }

    fun createButton(context:Context, viewGroup: ViewGroup) : Button {
        var inflater : LayoutInflater = LayoutInflater.from(context)
        var button = inflater.inflate(R.layout.fragment_button, viewGroup, false) as Button
        return button
    }
}
package com.example.earsensei

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button

class ButtonsGridCreator(context: Context, viewGroup: ViewGroup, notes : List<String>)  {

    lateinit var allButtons : List<Button>

    init{
        allButtons = createButtonsGrid(context, viewGroup, notes)
    }


    fun createButtonsGrid(context: Context, viewGroup: ViewGroup, notes : List<String>) : MutableList<Button>{
        val buttons : MutableList<Button> = mutableListOf()
        notes.forEach{
            var button = createButton(context, viewGroup, it)
            viewGroup.addView(button)
            buttons.add(button)
        }
        return buttons
    }

    fun createButton(context:Context, viewGroup: ViewGroup, buttonText: String) : Button {
        var inflater : LayoutInflater = LayoutInflater.from(context)
        var button = inflater.inflate(R.layout.fragment_button, viewGroup, false) as Button
        button.setText(buttonText)
//        button.setOnClickListener(){
//            //Toast.makeText(context, buttonText, Toast.LENGTH_LONG).show()
//
//        }
        return button
    }
}
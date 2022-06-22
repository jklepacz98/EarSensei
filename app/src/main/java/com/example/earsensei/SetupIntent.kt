package com.example.earsensei

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Button


object SetupIntent {
    fun startNewActivity(context: Context,activityClass: Class<out Activity>){
        val intent = Intent(context, activityClass)
        context.startActivity(intent)
    }
}
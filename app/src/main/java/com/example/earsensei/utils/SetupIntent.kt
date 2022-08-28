package com.example.earsensei.utils

import android.app.Activity
import android.content.Context
import android.content.Intent


object SetupIntent {
    fun startNewActivity(context: Context, activityClass: Class<out Activity>) {
        val intent = Intent(context, activityClass)
        context.startActivity(intent)
    }
}
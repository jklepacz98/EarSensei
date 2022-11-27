package com.example.earsensei.utils

import androidx.recyclerview.widget.DiffUtil

fun <T : Any> getDiffCallback(compare: ((oldItem: T, newItem: T) -> Boolean)? = null) =
    object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            compare?.invoke(oldItem, newItem) ?: false

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
    }

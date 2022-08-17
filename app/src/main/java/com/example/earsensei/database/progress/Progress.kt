package com.example.earsensei.database.progress

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Progress(
    @PrimaryKey var id: Int,
    @ColumnInfo var type: String,
    @ColumnInfo var question: String,
    @ColumnInfo var date: Int
)
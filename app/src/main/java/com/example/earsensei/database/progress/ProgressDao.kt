package com.example.earsensei.database.progress

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProgressDao {
    @Query("SELECT * FROM progress")
    fun readAllData(): LiveData<List<Progress>>

    @Insert()
    suspend fun insert(vararg progress: Progress)
}
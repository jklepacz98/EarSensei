package com.example.earsensei.database.progression

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProgressionDao {
    @Query("SELECT * FROM progression")
    fun readAllData(): List<Progression>

    @Insert()
    suspend fun insert(vararg progressions: Progression)
}
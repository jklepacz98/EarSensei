package com.example.earsensei.database.unlockedQuestion

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UnlockedQuestionDao {
    @Query("SELECT * FROM unlockedQuestion")
    fun getAllData(): List<UnlockedQuestion>

    @Query("SELECT * FROM unlockedQuestion WHERE :type = type")
    suspend fun getByType(type: String): List<UnlockedQuestion>

    @Query("SELECT * FROM unlockedQuestion WHERE :type = type AND :after > date")
    fun getByTypeAfterDate(type: String, after: Long): List<UnlockedQuestion>

    @Query("SELECT MAX(date) FROM unlockedQuestion WHERE :type = type")
    fun getLatest(type: String): Long

    @Insert()
    suspend fun insert(vararg unlockedQuestions: UnlockedQuestion)

    @Query("DELETE FROM unlockedQuestion")
    suspend fun deleteAll()
}
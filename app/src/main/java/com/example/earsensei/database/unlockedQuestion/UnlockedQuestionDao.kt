package com.example.earsensei.database.unlockedQuestion

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UnlockedQuestionDao {
    @Query("SELECT * FROM unlockedquestion")
    fun getAllData(): List<UnlockedQuestion>

    @Query("SELECT * FROM unlockedquestion WHERE :type = type")
    fun getByType(type: String): List<UnlockedQuestion>

    @Query("SELECT * FROM unlockedquestion WHERE :type = type AND :after > date")
    fun getByTypeAfterDate(type: String, after: Long): List<UnlockedQuestion>

    @Query("SELECT MAX(date) FROM unlockedquestion WHERE :type = type")
    fun getLatest(type: String): Long

    @Insert()
    suspend fun insert(vararg unlockedquestions: UnlockedQuestion)

    @Query("DELETE FROM unlockedquestion")
    suspend fun deleteAll()
}
package com.example.earsensei.database.progression

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProgressionDao {
    @Query("SELECT * FROM progression")
    fun getAllData(): List<Progression>

    @Query("SELECT * FROM progression WHERE :type = type")
    fun getByType(type: String): List<Progression>

    @Query("SELECT * FROM progression WHERE :type = type AND :after > date")
    fun getByTypeAfterDate(type: String, after: Long): List<Progression>

    @Query("SELECT MAX(date) FROM progression WHERE :type = type")
    fun getLatest(type: String): Long

    @Insert()
    suspend fun insert(vararg progressions: Progression)

    @Query("DELETE FROM progression")
    suspend fun deleteAll()
}
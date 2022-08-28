package com.example.earsensei.database.progression

class ProgressionRepository(private val progressionDao: ProgressionDao) {
    val readAllData: List<Progression> = progressionDao.getAllData()

    suspend fun addProgress(progression: Progression) {
        progressionDao.insert(progression)
    }
}
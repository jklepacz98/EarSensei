package com.example.earsensei.database.progress

import androidx.lifecycle.LiveData

class ProgressRepository(private val progressDao: ProgressDao) {
    val readAllData: LiveData<List<Progress>> = progressDao.readAllData()

    suspend fun addProgress(progress: Progress) {
        progressDao.insert(progress)
    }
}
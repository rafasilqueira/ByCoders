package com.example.bycoders.data.repository.implementation

import com.example.bycoders.data.dao.HistoryDao
import com.example.bycoders.data.repository.abstraction.HistoryRepository
import com.example.bycoders.domain.model.HistoryModel
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyDao: HistoryDao
) : HistoryRepository {
    override suspend fun insert(historyModel: HistoryModel) {
        historyDao.insert(historyModel)
    }
}
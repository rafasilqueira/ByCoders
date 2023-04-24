package com.example.bycoders.data.repository.abstraction

import com.example.bycoders.domain.model.HistoryModel

interface HistoryRepository {

    suspend fun insert(historyModel: HistoryModel)

}
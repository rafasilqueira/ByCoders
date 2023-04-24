package com.example.bycoders.domain.usecase.implementation

import com.example.bycoders.data.repository.abstraction.HistoryRepository
import com.example.bycoders.domain.model.HistoryModel
import com.example.bycoders.domain.usecase.abstraction.InsertHistoryUseCase
import javax.inject.Inject

class InsertHistoryUseCaseImpl @Inject constructor(
    private val historyRepository: HistoryRepository
) : InsertHistoryUseCase {

    override suspend fun invoke(historyModel: HistoryModel) {
        historyRepository.insert(historyModel)
    }

}
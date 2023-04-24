package com.example.bycoders.domain.usecase.abstraction

import com.example.bycoders.domain.model.HistoryModel

interface InsertHistoryUseCase {

    suspend operator fun invoke(historyModel: HistoryModel)

}
package com.example.bycoders.presentation.vm.implementation

import androidx.lifecycle.viewModelScope
import com.example.bycoders.domain.model.HistoryModel
import com.example.bycoders.domain.usecase.abstraction.GetCurrentUserUseCase
import com.example.bycoders.domain.usecase.abstraction.InsertHistoryUseCase
import com.example.bycoders.presentation.vm.abstraction.HomeVM
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVMImpl @Inject constructor(
    private val insertHistoryUseCase: InsertHistoryUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : HomeVM() {

    override fun insertHistory(historyModel: HistoryModel) {
        viewModelScope.launch(Dispatchers.IO) {
            insertHistoryUseCase(historyModel)
        }
    }

    override fun getCurrentUser(): FirebaseUser? {
        return getCurrentUserUseCase()
    }
}
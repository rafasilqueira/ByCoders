package com.example.bycoders.presentation.vm.abstraction

import androidx.lifecycle.ViewModel
import com.example.bycoders.domain.model.HistoryModel
import com.google.firebase.auth.FirebaseUser

abstract class HomeVM : ViewModel() {
    abstract fun insertHistory(historyModel: HistoryModel)
    abstract fun getCurrentUser(): FirebaseUser?
}
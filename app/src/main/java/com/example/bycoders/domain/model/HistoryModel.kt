package com.example.bycoders.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class HistoryModel(
    @PrimaryKey val id: Long? = null,
    val email: String,
    val latitude: Double,
    val longitude: Double
)

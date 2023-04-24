package com.example.bycoders.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bycoders.data.dao.HistoryDao
import com.example.bycoders.domain.model.HistoryModel

@Database(entities = [HistoryModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                return instance ?: buildRoomDatabase(context).also { instance = it }
            }
        }

        private fun buildRoomDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "db"
        ).build()
    }

}
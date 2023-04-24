package com.example.bycoders.di

import android.app.Application
import com.example.bycoders.data.AppDatabase
import com.example.bycoders.data.dao.HistoryDao
import com.example.bycoders.data.firebase.AuthenticationFirebase
import com.example.bycoders.data.repository.abstraction.AuthenticationRepository
import com.example.bycoders.data.repository.abstraction.HistoryRepository
import com.example.bycoders.data.repository.implementation.AuthenticationRepositoryImpl
import com.example.bycoders.data.repository.implementation.HistoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesAuthFirebase(): AuthenticationFirebase = AuthenticationFirebase()

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun providesHistoryDao(appDatabase: AppDatabase) = appDatabase.historyDao()

    @Provides
    @Singleton
    fun providesAuthenticationRepository(
        authenticationFirebase: AuthenticationFirebase
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(authenticationFirebase)
    }

    @Provides
    @Singleton
    fun providesHistoryRepository(historyDao: HistoryDao): HistoryRepository {
        return HistoryRepositoryImpl(historyDao)
    }

}
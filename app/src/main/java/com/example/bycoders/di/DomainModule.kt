package com.example.bycoders.di

import com.example.bycoders.data.repository.abstraction.AuthenticationRepository
import com.example.bycoders.data.repository.abstraction.HistoryRepository
import com.example.bycoders.domain.usecase.abstraction.CreateUserUseCase
import com.example.bycoders.domain.usecase.abstraction.GetCurrentUserUseCase
import com.example.bycoders.domain.usecase.abstraction.InsertHistoryUseCase
import com.example.bycoders.domain.usecase.abstraction.IsUserExistsUseCase
import com.example.bycoders.domain.usecase.abstraction.IsUserLoggedUseCase
import com.example.bycoders.domain.usecase.abstraction.SignInUseCase
import com.example.bycoders.domain.usecase.implementation.CreateUserUseCaseImpl
import com.example.bycoders.domain.usecase.implementation.GetCurrentUserUseCaseImpl
import com.example.bycoders.domain.usecase.implementation.InsertHistoryUseCaseImpl
import com.example.bycoders.domain.usecase.implementation.IsUserExistsUseCaseImpl
import com.example.bycoders.domain.usecase.implementation.IsUserLoggedUseCaseImpl
import com.example.bycoders.domain.usecase.implementation.SignInUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun providesIsUserLoggedUseCase(authenticationRepository: AuthenticationRepository): IsUserLoggedUseCase {
        return IsUserLoggedUseCaseImpl(authenticationRepository)
    }

    @Provides
    @Singleton
    fun providesCreateUserUseCase(authenticationRepository: AuthenticationRepository): CreateUserUseCase {
        return CreateUserUseCaseImpl(authenticationRepository)
    }

    @Provides
    @Singleton
    fun providesSignInUseCase(authenticationRepository: AuthenticationRepository): SignInUseCase {
        return SignInUseCaseImpl(authenticationRepository)
    }

    @Provides
    @Singleton
    fun providesIsUserExistsUseCase(authenticationRepository: AuthenticationRepository): IsUserExistsUseCase {
        return IsUserExistsUseCaseImpl(authenticationRepository)
    }

    @Provides
    @Singleton
    fun getCurrentUserUseCase(authenticationRepository: AuthenticationRepository): GetCurrentUserUseCase {
        return GetCurrentUserUseCaseImpl(authenticationRepository)
    }

    @Provides
    @Singleton
    fun providesInsertHistoryUse(historyRepository: HistoryRepository): InsertHistoryUseCase {
        return InsertHistoryUseCaseImpl(historyRepository)
    }

}
package com.capg.insurance.di

import com.capg.insurance.data.repository.AuthRepository
import com.capg.insurance.data.repository.BaseAuthRepository
import com.capg.insurance.data.repository.firebase.BaseAuthenticator
import com.capg.insurance.data.repository.firebase.FirebaseAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAuthenticator() : BaseAuthenticator{
        return  FirebaseAuthenticator()
    }

    @Singleton
    @Provides
    fun provideRepository(
        authenticator : BaseAuthenticator
    ) : BaseAuthRepository {
        return AuthRepository(authenticator)
    }
}
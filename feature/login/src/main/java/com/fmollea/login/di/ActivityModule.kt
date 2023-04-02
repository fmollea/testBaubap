package com.fmollea.login.di

import com.fmollea.domain.usecases.login.LoginUseCase
import com.fmollea.domain.usecases.login.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindLoginUseCase(loginUseCase: LoginUseCaseImpl): LoginUseCase
}
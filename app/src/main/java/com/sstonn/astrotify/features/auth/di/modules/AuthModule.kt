package com.sstonn.astrotify.features.auth.di.modules

import com.sstonn.astrotify.features.auth.data.AuthRepository
import com.sstonn.astrotify.features.auth.data.repositories.DefaultAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthDependenciesModule{
    @Binds
    abstract fun bindAuthRepository(authRepository: DefaultAuthRepository): AuthRepository
}

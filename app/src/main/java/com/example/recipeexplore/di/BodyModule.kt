package com.example.recipeexplore.di

import com.example.recipeexplore.models.register.RegisterRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(FragmentComponent::class)
object BodyModule {

    @Provides
    fun provideRegisterRequest() = RegisterRequest()
}
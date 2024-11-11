package com.example.recipeexplore.data.repository

import com.example.recipeexplore.data.source.RemoteDataSource
import com.example.recipeexplore.models.register.RegisterRequest
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RegisterRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun postRegister(apiKey: String, body: RegisterRequest) =
        remoteDataSource.postRegister(apiKey, body)
}
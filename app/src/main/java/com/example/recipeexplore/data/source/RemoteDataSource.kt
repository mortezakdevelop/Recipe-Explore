package com.example.recipeexplore.data.source

import com.example.recipeexplore.data.network.ApiServices
import com.example.recipeexplore.models.register.RegisterRequest
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiServices: ApiServices
) {
    suspend fun postRegister(apiKey: String, body: RegisterRequest) =
        apiServices.postRegister(apiKey, body)
}
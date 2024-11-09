package com.example.recipeexplore.data.network

import com.example.recipeexplore.models.register.RegisterRequest
import com.example.recipeexplore.models.register.RegisterResponse
import com.example.recipeexplore.urils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {
    @POST("users/connect")
    suspend fun postRegister(
        @Query(Constants.API_KEY) apiKey:String,
        @Body body:RegisterRequest
    ):Response<RegisterResponse>
}
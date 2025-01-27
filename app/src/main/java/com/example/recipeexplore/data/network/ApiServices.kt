package com.example.recipeexplore.data.network

import com.example.recipeexplore.models.register.RegisterRequest
import com.example.recipeexplore.models.register.RegisterResponse
import com.example.recipeexplore.models.register.recipe.ApodResponse
import com.example.recipeexplore.models.register.recipe.ResponseRecipes
import com.example.recipeexplore.urils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiServices {
    @POST("users/connect")
    suspend fun postRegister(
        @Query(API_KEY) apiKey: String,
        @Body body: RegisterRequest
    ): Response<RegisterResponse>

    @GET("recipes/complexSearch")
    suspend fun getRecipes(@QueryMap queries: Map<String, String>): Response<ResponseRecipes>

    @GET("planetary/apod")
    suspend fun getApod(
        @Query("api_key") apiKey: String = "DEMO_KEY",
        @Query("date") date: String? = null // اضافه کردن پارامتر تاریخ
    ): ApodResponse

}
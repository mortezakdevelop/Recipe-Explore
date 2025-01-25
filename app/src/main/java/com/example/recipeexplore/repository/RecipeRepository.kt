package com.example.recipeexplore.repository

import android.util.Log
import com.example.recipeexplore.data.network.ApiServices
import com.example.recipeexplore.models.register.recipe.ApodResponse
import com.example.recipeexplore.models.register.recipe.ResponseRecipes
import retrofit2.Response
import javax.inject.Inject

class RecipesRepository @Inject constructor(private val apiService: ApiServices) {
    suspend fun getRecipes(queries: Map<String, String>): Response<ResponseRecipes> {
        return apiService.getRecipes(queries)
    }

    suspend fun getMultipleApods(): List<ApodResponse> {
        val dates = listOf("2023-10-25", "2023-10-24", "2023-10-23") // تاریخ‌های مورد نظر
        val apodList = mutableListOf<ApodResponse>()

        for (date in dates) {
            try {
                val apod = apiService.getApod(date = date)
                apodList.add(apod)
            } catch (e: Exception) {
                Log.e("ApodRepository", "Error fetching APOD for date $date", e)
            }
        }

        return apodList
    }

}
package com.example.recipeexplore.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.recipeexplore.data.network.ApiServices
import com.example.recipeexplore.data.source.RemoteDataSource
import com.example.recipeexplore.models.register.recipe.ApodResponse
import com.example.recipeexplore.models.register.recipe.ResponseRecipes
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class RecipeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getRecipes(apiKey:String,queries: Map<String, String>): Response<ResponseRecipes> {
        return remoteDataSource.getRecipes(apiKey,queries)
    }
}
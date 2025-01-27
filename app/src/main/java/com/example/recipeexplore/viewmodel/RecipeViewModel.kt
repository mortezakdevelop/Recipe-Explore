package com.example.recipeexplore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeexplore.models.register.recipe.ResponseRecipes
import com.example.recipeexplore.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository,
) : ViewModel() {
    private val _recipes = MutableStateFlow<Result<List<ResponseRecipes.Result>>>(Result.Loading)
    val recipes: StateFlow<Result<List<ResponseRecipes.Result>>> = _recipes.asStateFlow()

    fun fetchRecipes(apiKey: String, queries: Map<String, String>) {
        viewModelScope.launch {
            try {
                val response = repository.getRecipes(apiKey, queries)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.results?.isNotEmpty() == true) {
                        // مقدار results به StateFlow اضافه شود
                        _recipes.value = Result.Success(body.results)
                    } else {
                        _recipes.value = Result.Error("Empty results")
                    }
                } else {
                    _recipes.value = Result.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _recipes.value = Result.Error("Exception: ${e.localizedMessage}")
            }
        }
    }
}

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}



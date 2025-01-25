package com.example.recipeexplore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeexplore.models.register.recipe.ApodResponse
import com.example.recipeexplore.models.register.recipe.ResponseRecipes
import com.example.recipeexplore.repository.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipesRepository,
) : ViewModel() {
    private val _recipes = MutableLiveData<List<ResponseRecipes.Result>>()
    val recipes: LiveData<List<ResponseRecipes.Result>> = _recipes

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _apodList = MutableLiveData<List<ApodResponse>>()
    val apodList: LiveData<List<ApodResponse>> get() = _apodList

    fun fetchApod() {
        viewModelScope.launch {
            try {
                val response = repository.getMultipleApods()
                _apodList.value = response
            } catch (e: Exception) {
                Log.e("ApodViewModel", "Error fetching APOD data", e)
            }
        }
    }
}

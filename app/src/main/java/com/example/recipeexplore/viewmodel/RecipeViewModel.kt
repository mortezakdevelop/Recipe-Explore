package com.example.recipeexplore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeexplore.models.register.RegisterRequest
import com.example.recipeexplore.models.register.recipe.ApodResponse
import com.example.recipeexplore.models.register.recipe.ResponseRecipes
import com.example.recipeexplore.repository.RecipeRepository
import com.example.recipeexplore.urils.NetworkResponseCode
import com.example.recipeexplore.urils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository,
) : ViewModel() {
    private val _recipes = MutableLiveData<List<ResponseRecipes.Result>>()
    val recipes: LiveData<List<ResponseRecipes.Result>> = _recipes

}

package com.example.recipeexplore.models.register


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("hash")
    val hash: String?,
    @SerializedName("spoonacularPassword")
    val spoonacularPassword: String?,
    @SerializedName("username")
    val username: String?
)
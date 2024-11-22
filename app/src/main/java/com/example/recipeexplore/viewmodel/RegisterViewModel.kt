package com.example.recipeexplore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeexplore.data.repository.RegisterRepository
import com.example.recipeexplore.models.register.RegisterRequest
import com.example.recipeexplore.models.register.RegisterResponse
import com.example.recipeexplore.urils.NetworkResponseCode
import com.example.recipeexplore.urils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
) : ViewModel() {

    val registerLiveData = MutableLiveData<NetworkState<RegisterResponse>>()

    fun callRegisterApi(apiKey: String, bodyRegister: RegisterRequest) =
        viewModelScope.launch(Dispatchers.IO) {
            registerLiveData.postValue(NetworkState.Loading())
            val response = registerRepository.postRegister(apiKey, bodyRegister)
            registerLiveData.postValue(NetworkResponseCode(response).generalNetworkResponse())
        }


    fun saveRegisterUserData(username:String , hash: String) =
        viewModelScope.launch {
            registerRepository.saveRegisterUserData(username,hash)
        }


    val readRegisterUserData = registerRepository.readRegisterUserData
}
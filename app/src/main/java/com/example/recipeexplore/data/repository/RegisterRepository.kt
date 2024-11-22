package com.example.recipeexplore.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.recipeexplore.data.source.RemoteDataSource
import com.example.recipeexplore.models.register.RegisterRequest
import com.example.recipeexplore.models.register.RegisterStoredModel
import com.example.recipeexplore.urils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ActivityRetainedScoped
class RegisterRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val remoteDataSource: RemoteDataSource
) {

    //data store
    private object StoredKey {
        val username = stringPreferencesKey(Constants.REGISTER_USERNAME)
        val hash = stringPreferencesKey(Constants.REGISTER_HASH)
    }

    //create dataStore
    private val Context.createDataStore: DataStore<Preferences> by preferencesDataStore(Constants.REGISTER_USER_INFO)

    suspend fun saveRegisterUserData(username: String, hash: String) {
        context.createDataStore.edit {
            it[StoredKey.username] = username
            it[StoredKey.hash] = hash
        }
    }

    val readRegisterUserData: Flow<RegisterStoredModel> = context.createDataStore.data
        .catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            val username = it[StoredKey.username] ?: ""
            val hash = it[StoredKey.hash] ?: ""
            RegisterStoredModel(username,hash)
        }

    suspend fun postRegister(apiKey: String, body: RegisterRequest) =
        remoteDataSource.postRegister(apiKey, body)
}
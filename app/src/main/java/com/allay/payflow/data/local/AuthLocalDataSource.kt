package com.allay.payflow.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.authDataStore by preferencesDataStore(
    name = "auth_preferences"
)

class AuthLocalDataSource(
    private val context: Context
) {

    private object Keys {
        val USER_ID = stringPreferencesKey("user_id")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
    }

    suspend fun saveUser(
        id: String,
        name: String,
        email: String
    ) {
        context.authDataStore.edit { preferences ->
            preferences[Keys.USER_ID] = id
            preferences[Keys.USER_NAME] = name
            preferences[Keys.USER_EMAIL] = email
        }
    }

    suspend fun getUser(): SavedUser? {
        val preferences = context.authDataStore.data.first()

        val id = preferences[Keys.USER_ID] ?: return null
        val name = preferences[Keys.USER_NAME] ?: return null
        val email = preferences[Keys.USER_EMAIL] ?: return null

        return SavedUser(
            id = id,
            name = name,
            email = email
        )
    }

    suspend fun clearUser() {
        context.authDataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

data class SavedUser(
    val id: String,
    val name: String,
    val email: String
)
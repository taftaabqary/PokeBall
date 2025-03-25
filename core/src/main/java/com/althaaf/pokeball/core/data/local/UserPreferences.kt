package com.althaaf.pokeball.core.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("setting")
class UserPreferences(private val dataStore: DataStore<Preferences>) {

    fun getInfoLogin(): Flow<Boolean> {
        return dataStore.data.map {
            it[USER_IS_LOGIN] ?: false
        }
    }

    suspend fun setInfoLogin(isLogin: Boolean) {
        dataStore.edit {
            it[USER_IS_LOGIN] = isLogin
        }
    }

    companion object {
        val USER_IS_LOGIN = booleanPreferencesKey("isLogin")
    }
}
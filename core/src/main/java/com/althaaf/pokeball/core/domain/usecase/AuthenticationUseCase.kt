package com.althaaf.pokeball.core.domain.usecase

import com.althaaf.pokeball.core.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationUseCase {
    fun loginUser(username: String, password: String): Flow<User>
    fun getLoginInfo(): Flow<Boolean>
    suspend fun registerUser(username: String, password: String)
    suspend fun setLoginInfo(isLogin: Boolean)
}
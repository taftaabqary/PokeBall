package com.althaaf.pokeball.core.repository

import com.althaaf.pokeball.core.data.local.UserDao
import com.althaaf.pokeball.core.domain.entity.User
import com.althaaf.pokeball.core.domain.repository.IAuthenticationRepository
import kotlinx.coroutines.flow.Flow

class AuthRepository(
    private val dao: UserDao
): IAuthenticationRepository {
    override fun loginUser(username: String, password: String): Flow<User> {
        TODO("Not yet implemented")
    }

    override fun getLoginInfo(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun registerUser(username: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setLoginInfo(isLogin: Boolean) {
        TODO("Not yet implemented")
    }
}
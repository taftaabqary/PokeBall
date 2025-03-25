package com.althaaf.pokeball.core.domain.usecase

import com.althaaf.pokeball.core.domain.entity.User
import com.althaaf.pokeball.core.domain.repository.IAuthenticationRepository
import kotlinx.coroutines.flow.Flow

class AuthenticationInteractor(private val iAuthenticationRepository: IAuthenticationRepository): AuthenticationUseCase {
    override fun loginUser(username: String, password: String): Flow<User> {
        return iAuthenticationRepository.loginUser(username, password)
    }

    override suspend fun registerUser(username: String, password: String) {
        iAuthenticationRepository.registerUser(username, password)
    }

    override fun getLoginInfo(): Flow<Boolean> {
        return iAuthenticationRepository.getLoginInfo()
    }

    override suspend fun setLoginInfo(isLogin: Boolean) {
        return iAuthenticationRepository.setLoginInfo(isLogin)
    }
}
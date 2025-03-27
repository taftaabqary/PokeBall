package com.althaaf.pokeball.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaaf.pokeball.core.domain.entity.User
import com.althaaf.pokeball.core.domain.usecase.AuthenticationUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AuthViewModel(private val authenticationUseCase: AuthenticationUseCase): ViewModel() {

    fun registerUser(username: String, password: String) {
        viewModelScope.launch {
            authenticationUseCase.registerUser(username, password)
        }
    }

    fun login(username: String, password: String): Flow<User> {
        return authenticationUseCase.loginUser(username, password)
    }

    fun saveLogin(isLogin: Boolean) {
        viewModelScope.launch {
            authenticationUseCase.setLoginInfo(isLogin)
        }
    }
}
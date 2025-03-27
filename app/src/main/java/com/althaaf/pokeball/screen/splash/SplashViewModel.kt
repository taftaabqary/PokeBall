package com.althaaf.pokeball.screen.splash

import androidx.lifecycle.ViewModel
import com.althaaf.pokeball.core.domain.usecase.AuthenticationUseCase
import kotlinx.coroutines.flow.Flow

class SplashViewModel(private val authenticationUseCase: AuthenticationUseCase): ViewModel() {
    fun checkLogin(): Flow<Boolean> {
        return authenticationUseCase.getLoginInfo()
    }
}
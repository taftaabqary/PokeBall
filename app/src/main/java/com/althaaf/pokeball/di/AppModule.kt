package com.althaaf.pokeball.di

import com.althaaf.pokeball.core.domain.usecase.AuthenticationInteractor
import com.althaaf.pokeball.core.domain.usecase.AuthenticationUseCase
import com.althaaf.pokeball.core.domain.usecase.PokeBallInteractor
import com.althaaf.pokeball.core.domain.usecase.PokeBallUseCase
import com.althaaf.pokeball.screen.auth.AuthViewModel
import com.althaaf.pokeball.screen.home.MainViewModel
import com.althaaf.pokeball.screen.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SplashViewModel(get())
    }

    viewModel {
        MainViewModel(get())
    }

    viewModel {
        AuthViewModel(get())
    }
}

val useCaseModule = module {
    factory<AuthenticationUseCase> {
        AuthenticationInteractor(get())
    }

    factory<PokeBallUseCase> {
        PokeBallInteractor(get())
    }
}
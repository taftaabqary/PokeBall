package com.althaaf.pokeball.core.repository

import com.althaaf.pokeball.core.data.network.ApiService
import com.althaaf.pokeball.core.domain.repository.IPokeBallRepository

class MainRepository(
    private val apiService: ApiService
): IPokeBallRepository {
}
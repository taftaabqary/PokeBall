package com.althaaf.pokeball.core.repository

import androidx.paging.PagingData
import com.althaaf.pokeball.core.data.network.ApiService
import com.althaaf.pokeball.core.domain.entity.Ability
import com.althaaf.pokeball.core.domain.entity.PokeBall
import com.althaaf.pokeball.core.domain.repository.IPokeBallRepository
import com.althaaf.pokeball.core.utils.ApiResult
import kotlinx.coroutines.flow.Flow

class MainRepository(
    private val apiService: ApiService
): IPokeBallRepository {
    override suspend fun getListPokemon(): Flow<PagingData<PokeBall>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailAbilityPokemon(id: Int): Flow<ApiResult<Ability>> {
        TODO("Not yet implemented")
    }
}
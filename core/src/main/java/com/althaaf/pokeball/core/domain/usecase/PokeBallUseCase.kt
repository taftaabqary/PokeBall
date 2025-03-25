package com.althaaf.pokeball.core.domain.usecase

import androidx.paging.PagingData
import com.althaaf.pokeball.core.domain.entity.Ability
import com.althaaf.pokeball.core.domain.entity.PokeBall
import com.althaaf.pokeball.core.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface PokeBallUseCase {
    suspend fun getListPokemon(): Flow<PagingData<PokeBall>>
    suspend fun getDetailAbilityPokemon(id: Int): Flow<ApiResult<Ability>>
}
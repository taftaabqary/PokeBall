package com.althaaf.pokeball.core.domain.repository

import androidx.paging.PagingData
import com.althaaf.pokeball.core.domain.entity.DetailPokemon
import com.althaaf.pokeball.core.domain.entity.PokeBall
import com.althaaf.pokeball.core.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface IPokeBallRepository {
    suspend fun getListPokemon(): Flow<PagingData<PokeBall>>
    suspend fun getDetailAbilityPokemon(id: Int): Flow<ApiResult<DetailPokemon>>
}
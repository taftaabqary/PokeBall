package com.althaaf.pokeball.core.domain.usecase

import androidx.paging.PagingData
import com.althaaf.pokeball.core.domain.entity.Ability
import com.althaaf.pokeball.core.domain.entity.PokeBall
import com.althaaf.pokeball.core.domain.repository.IPokeBallRepository
import com.althaaf.pokeball.core.utils.ApiResult
import kotlinx.coroutines.flow.Flow

class PokeBallInteractor(private val iPokeBallRepository: IPokeBallRepository): PokeBallUseCase {
    override suspend fun getListPokemon(): Flow<PagingData<PokeBall>> {
        return iPokeBallRepository.getListPokemon()
    }

    override suspend fun getDetailAbilityPokemon(id: Int): Flow<ApiResult<Ability>> {
        return iPokeBallRepository.getDetailAbilityPokemon(id)
    }
}
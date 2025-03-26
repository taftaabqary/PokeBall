package com.althaaf.pokeball.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.althaaf.pokeball.core.adapter.PokeBallPagingSource
import com.althaaf.pokeball.core.data.network.ApiService
import com.althaaf.pokeball.core.domain.entity.DetailPokemon
import com.althaaf.pokeball.core.domain.entity.PokeBall
import com.althaaf.pokeball.core.domain.repository.IPokeBallRepository
import com.althaaf.pokeball.core.utils.ApiResult
import com.althaaf.pokeball.core.utils.MapConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository(
    private val apiService: ApiService
): IPokeBallRepository {
    override suspend fun getListPokemon(): Flow<PagingData<PokeBall>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                PokeBallPagingSource(apiService)
            }
        ).flow
    }

    override suspend fun getDetailAbilityPokemon(id: Int): Flow<ApiResult<DetailPokemon>> = flow {
        try {
            emit(ApiResult.Loading)
            val response = apiService.getPokemonById(id)
            if(response.isSuccessful) {
                val responseBody = response.body()
                if(responseBody != null) {
                    emit(ApiResult.Success(MapConverter.detailResponseToDataDomain(responseBody)))
                } else {
                    emit(ApiResult .Empty)
                }
            } else {
                val errorBody = response.errorBody()
            }

        } catch (e: Exception) {
            emit(ApiResult.Error(exception = e, message = e.message.toString()))
        }
    }
}
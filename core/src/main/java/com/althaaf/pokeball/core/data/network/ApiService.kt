package com.althaaf.pokeball.core.data.network

import com.althaaf.pokeball.core.model.ability.AbilityResponse
import com.althaaf.pokeball.core.model.list.PokeBallResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("ability/")
    suspend fun getListAbilityPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<PokeBallResponse>

    @GET("ability/{id}")
    suspend fun getAbilityPokemonById(
        @Path("id") id: Int
    ): Response<AbilityResponse>

}
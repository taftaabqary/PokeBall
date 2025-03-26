package com.althaaf.pokeball.core.data.network

import com.althaaf.pokeball.core.model.detail.DetailResponse
import com.althaaf.pokeball.core.model.list.PokeBallResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon/")
    suspend fun getListPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<PokeBallResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: Int
    ): Response<DetailResponse>

}
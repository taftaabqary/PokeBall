package com.althaaf.pokeball.core.model.list

data class PokeBallResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
)
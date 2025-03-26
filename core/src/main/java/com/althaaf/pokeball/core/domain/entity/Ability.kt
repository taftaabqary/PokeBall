package com.althaaf.pokeball.core.domain.entity

data class DetailPokemon(
    val name: String,
    val id: Int,
    val abilities: List<Abilities>
)

data class Abilities(
    val name: String,
    val url: String,
    val is_hidden: Boolean,
    val slot: Int
)
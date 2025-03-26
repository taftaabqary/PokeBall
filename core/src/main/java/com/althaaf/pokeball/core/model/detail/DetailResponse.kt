package com.althaaf.pokeball.core.model.detail

data class DetailResponse(
    val abilities: List<Ability>,
    val height: Int,
    val id: Int,
    val name: String,
    val weight: Int
)
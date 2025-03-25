package com.althaaf.pokeball.core.domain.entity

import com.althaaf.pokeball.core.model.ability.EffectChange
import com.althaaf.pokeball.core.model.ability.EffectEntryX
import com.althaaf.pokeball.core.model.ability.FlavorTextEntry

data class Ability(
    val name: String,
    val effectChanges: List<EffectChange>,
    val effectEntries: List<EffectEntryX>,
    val flavorTextEntry: List<FlavorTextEntry>,
)
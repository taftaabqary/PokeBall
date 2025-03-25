package com.althaaf.pokeball.core.model.ability

data class EffectChange(
    val effect_entries: List<EffectEntry>,
    val version_group: VersionGroup
)
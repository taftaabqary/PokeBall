package com.althaaf.pokeball.core.utils

import com.althaaf.pokeball.core.data.local.UserEntity
import com.althaaf.pokeball.core.domain.entity.Abilities
import com.althaaf.pokeball.core.domain.entity.DetailPokemon
import com.althaaf.pokeball.core.domain.entity.PokeBall
import com.althaaf.pokeball.core.domain.entity.User
import com.althaaf.pokeball.core.model.detail.DetailResponse
import com.althaaf.pokeball.core.model.list.Result

object MapConverter {

    fun listResponseToDataDomain(result: List<Result>): List<PokeBall> {
        return result.map {
            PokeBall(
                name = it.name,
                url = it.url
            )
        }
    }

    fun detailResponseToDataDomain(data: DetailResponse): DetailPokemon {
        val abilities = data.abilities.map {
            Abilities(
                name = it.ability.name,
                url = it.ability.url,
                is_hidden = it.is_hidden,
                slot = it.slot
            )
        }

        return DetailPokemon(
            name = data.name,
            id = data.id,
            abilities = abilities
        )
    }

    fun userDomainToDataEntity(data: User): UserEntity {
        return UserEntity(
            username = data.username,
            password = data.password
        )
    }

    fun userEntityToDataDomain(data: UserEntity): User {
        return User(
            username = data.username,
            password = data.password
        )
    }
}
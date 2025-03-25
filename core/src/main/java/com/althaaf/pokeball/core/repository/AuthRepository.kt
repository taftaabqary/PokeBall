package com.althaaf.pokeball.core.repository

import com.althaaf.pokeball.core.data.local.UserDao
import com.althaaf.pokeball.core.domain.repository.IAuthenticationRepository

class AuthRepository(
    private val dao: UserDao
): IAuthenticationRepository {
}
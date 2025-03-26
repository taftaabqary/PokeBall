package com.althaaf.pokeball.core.repository

import com.althaaf.pokeball.core.data.local.UserDao
import com.althaaf.pokeball.core.data.local.UserPreferences
import com.althaaf.pokeball.core.domain.entity.User
import com.althaaf.pokeball.core.domain.repository.IAuthenticationRepository
import com.althaaf.pokeball.core.utils.MapConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepository(
    private val dao: UserDao,
    private val userPreferences: UserPreferences
): IAuthenticationRepository {
    override fun loginUser(username: String, password: String): Flow<User> {
        return dao.checkLogin(username, password)
            .map { item ->
                MapConverter.userEntityToDataDomain(data = item)
            }
    }

    override fun getLoginInfo(): Flow<Boolean> {
        return userPreferences.getInfoLogin()
    }

    override suspend fun registerUser(username: String, password: String) {
        val mapper = MapConverter.userDomainToDataEntity(User(username, password))
        dao.insertNewData(mapper)
    }

    override suspend fun setLoginInfo(isLogin: Boolean) {
        userPreferences.setInfoLogin(isLogin)
    }
}
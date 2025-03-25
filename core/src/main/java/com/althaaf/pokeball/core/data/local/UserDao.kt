package com.althaaf.pokeball.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewData(userEntity: UserEntity)

    @Query("SELECT * FROM user_tbl WHERE username = :username AND password = :password")
    fun insertNewData(username: String, password: String): Flow<UserEntity>
}
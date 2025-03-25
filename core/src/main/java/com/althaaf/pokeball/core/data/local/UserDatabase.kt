package com.althaaf.pokeball.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], exportSchema = false, version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}
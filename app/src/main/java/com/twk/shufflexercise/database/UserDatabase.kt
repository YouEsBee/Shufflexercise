package com.twk.shufflexercise.database

import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {
    abstract val dao: UserDao
}
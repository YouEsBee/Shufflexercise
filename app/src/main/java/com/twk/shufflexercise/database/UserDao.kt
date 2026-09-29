package com.twk.shufflexercise.database

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // suspend runs the function in quarantine, good for async

    // by default onConflict = OnConflictStrategy.ABORT
    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    // order by userName and ascending
    @Query("SELECT * FROM user ORDER BY userName ASC")
    fun orderUserByUserName(): Flow<List<User>>
}
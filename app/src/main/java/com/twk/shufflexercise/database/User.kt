package com.twk.shufflexercise.database

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity
data class User(
    val userName: String,
    val profilePicture: String? = null,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)
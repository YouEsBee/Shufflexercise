package com.twk.shufflexercise.database

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid:Int,
    @ColumnInfo(name = "userName") val userName: String?
)
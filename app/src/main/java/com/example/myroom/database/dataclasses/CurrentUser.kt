package com.example.myroom.database.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_user")
data class CurrentUser(
    @PrimaryKey
    val username: String
)


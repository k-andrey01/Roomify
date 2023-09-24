package com.example.myroom.database.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val username: String,
    val password: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

package com.example.myroom.database.repositories

import com.example.myroom.database.dao.UserDao
import com.example.myroom.database.dataclasses.User

class UserRepository(private val userDao: UserDao) {

    suspend fun loginUser(username: String, password: String): Boolean {
        val user = userDao.getUserByUsername(username)
        return user != null && user.password == password
    }

    suspend fun insertUser(user: User) {
        if (userDao.getUserByUsername(user.username) == null)
            userDao.insertUser(user)
    }

    suspend fun getUserByUsername(username: String): User?{
        return userDao.getUserByUsername(username)
    }
}

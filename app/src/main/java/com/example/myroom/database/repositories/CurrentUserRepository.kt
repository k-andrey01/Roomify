package com.example.myroom.database.repositories

import com.example.myroom.database.dao.CurrentUserDao
import com.example.myroom.database.dataclasses.CurrentUser

class CurrentUserRepository(private val currentUserDao: CurrentUserDao) {
    suspend fun insertCurrentUser(currentUser: CurrentUser){
        if (currentUserDao.getCurrentUser() == null)
            currentUserDao.insertCurrentUser(currentUser)
    }

    suspend fun getCurrentUser(): CurrentUser? {
        return currentUserDao.getCurrentUser()
    }

    suspend fun deleteCurrentUser(){
        currentUserDao.deleteCurrentUser()
    }
}
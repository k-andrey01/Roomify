package com.example.myroom.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myroom.database.dataclasses.CurrentUser
import com.example.myroom.database.dataclasses.User

@Dao
interface CurrentUserDao {
    @Query("SELECT * FROM current_user")
    suspend fun getCurrentUser(): CurrentUser?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCurrentUser(currentUser: CurrentUser)

    @Query("DELETE FROM current_user")
    suspend fun deleteCurrentUser()
}
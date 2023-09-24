package com.example.myroom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myroom.database.dao.MeetingRoomDao
import com.example.myroom.database.dao.UserDao
import com.example.myroom.database.dataclasses.MeetingRoom
import com.example.myroom.database.dataclasses.User

@Database(entities = [User::class, MeetingRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun meetingRoomDao(): MeetingRoomDao
}
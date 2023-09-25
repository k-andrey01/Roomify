package com.example.myroom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myroom.helpclasses.DateConverter
import com.example.myroom.database.dao.CurrentUserDao
import com.example.myroom.database.dao.MeetingRoomDao
import com.example.myroom.database.dao.ReservationDao
import com.example.myroom.database.dao.UserDao
import com.example.myroom.database.dataclasses.CurrentUser
import com.example.myroom.database.dataclasses.MeetingRoom
import com.example.myroom.database.dataclasses.Reservation
import com.example.myroom.database.dataclasses.User

@Database(entities = [User::class, MeetingRoom::class, CurrentUser::class, Reservation::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun meetingRoomDao(): MeetingRoomDao
    abstract fun currentUserDao(): CurrentUserDao
    abstract fun reservationDao(): ReservationDao
}
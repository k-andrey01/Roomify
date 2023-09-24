package com.example.myroom.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myroom.database.dataclasses.MeetingRoom

@Dao
interface MeetingRoomDao {
    @Query("SELECT * FROM meeting_rooms")
    suspend fun getAllMeetingRooms(): List<MeetingRoom>

    @Query("SELECT * FROM meeting_rooms WHERE id = :roomId")
    suspend fun getMeetingRoomById(roomId: Int): MeetingRoom?

    @Query("SELECT * FROM meeting_rooms WHERE roomName = :roomName")
    suspend fun getMeetingRoomByName(roomName: String): MeetingRoom?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMeetingRoom(room: MeetingRoom)

    @Delete
    suspend fun deleteMeetingRoom(room: MeetingRoom)
}
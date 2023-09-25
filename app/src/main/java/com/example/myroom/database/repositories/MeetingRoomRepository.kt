package com.example.myroom.database.repositories

import com.example.myroom.database.dao.MeetingRoomDao
import com.example.myroom.database.dataclasses.MeetingRoom

class MeetingRoomRepository(private val meetingRoomDao: MeetingRoomDao) {

    suspend fun getAllMeetingRooms(): List<MeetingRoom> {
        return meetingRoomDao.getAllMeetingRooms()
    }

    suspend fun getMeetingRoomById(roomId: Int): MeetingRoom?{
        return meetingRoomDao.getMeetingRoomById(roomId)
    }

    suspend fun insertMeetingRoom(room: MeetingRoom){
        if (meetingRoomDao.getMeetingRoomByName(room.roomName) == null)
            meetingRoomDao.insertMeetingRoom(room)
    }
}

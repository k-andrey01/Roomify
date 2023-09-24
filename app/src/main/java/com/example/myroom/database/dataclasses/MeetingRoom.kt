package com.example.myroom.database.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meeting_rooms")
data class MeetingRoom(
    val roomName: String,
    val numberOfChairs: Int,
    val hasProjector: Boolean,
    val hasWhiteboard: Boolean,
    val roomDescription: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
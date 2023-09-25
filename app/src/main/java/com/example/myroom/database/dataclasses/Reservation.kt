package com.example.myroom.database.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "reservations")
data class Reservation(
    val roomId: Int,
    val date: Date,
    val startTime: Date,
    val endTime: Date,
    val event: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var status: String = "В обработке"
}
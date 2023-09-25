package com.example.myroom.database.repositories

import com.example.myroom.database.dao.ReservationDao
import com.example.myroom.database.dataclasses.Reservation

class ReservationRepository(private val reservationDao: ReservationDao) {
    suspend fun insertReservation(reservation: Reservation){
        reservationDao.insertReservation(reservation)
    }

    suspend fun getReservationsByRoomId(roomId: Int): List<Reservation>?{
        return reservationDao.getReservationsByRoomId(roomId)
    }
}
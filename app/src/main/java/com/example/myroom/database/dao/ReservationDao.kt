package com.example.myroom.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myroom.database.dataclasses.Reservation

@Dao
interface ReservationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertReservation(reservation: Reservation)

    //ВООБЩЕ ДЛЯ ВЫВОДА В ТАБЛИЦУ НУЖНО ИСПОЛЬЗОВАТЬ ТОЛЬКО ПОДТВЕРЖДЕННЫЕ ЗАЯВКИ НА БРОНЬ КОМНАТЫ
    //НО В РАМКАХ ПРИЛОЖЕНИЯ БЫЛО ПРИНЯТО РЕШЕНИЕ ВЫВОДИТЬ ВСЕ, ТАК КАК НЕТ ЧАСТИ ОФИС-МЕНЕДЖЕРА
    @Query("SELECT * FROM reservations WHERE roomId = :roomId")
    suspend fun getReservationsByRoomId(roomId: Int): List<Reservation>
}
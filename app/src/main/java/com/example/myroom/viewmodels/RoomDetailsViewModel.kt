package com.example.myroom.viewmodels

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroom.activities.ReservationActivity
import com.example.myroom.database.dataclasses.MeetingRoom
import com.example.myroom.database.dataclasses.Reservation
import com.example.myroom.database.repositories.MeetingRoomRepository
import com.example.myroom.database.repositories.ReservationRepository
import kotlinx.coroutines.launch

class RoomDetailsViewModel(
    private val meetingRoomRepository: MeetingRoomRepository,
    private val reservationRepository: ReservationRepository,
    private val roomId: Int,
    private val context: Context
) : ViewModel() {

    val roomInfoLiveData = MutableLiveData<MeetingRoom?>()
    val reservationsLiveData = MutableLiveData<List<Reservation>?>()

    init {
        viewModelScope.launch {
            val roomInfo = meetingRoomRepository.getMeetingRoomById(roomId)
            roomInfoLiveData.postValue(roomInfo)

            val reservations = reservationRepository.getReservationsByRoomId(roomId)
            reservationsLiveData.postValue(reservations)
        }
    }

    fun bookReservation() {
        val intent = Intent(context, ReservationActivity::class.java)
        intent.putExtra("roomId", roomId)
        context.startActivity(intent)
        (context as Activity).finish()
    }
}

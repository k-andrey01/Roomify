package com.example.myroom.viewmodels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroom.ReservationActivity
import com.example.myroom.database.dataclasses.MeetingRoom
import com.example.myroom.database.repositories.MeetingRoomRepository
import kotlinx.coroutines.launch

class RoomDetailsViewModel(private val meetingRoomRepository: MeetingRoomRepository, private val roomId: Int, private val context: Context) : ViewModel() {

    val roomInfoLiveData = MutableLiveData<MeetingRoom?>()

    init {
        viewModelScope.launch {
            val roomInfo = meetingRoomRepository.getMeetingRoomById(roomId)
            roomInfoLiveData.postValue(roomInfo)
        }
    }

    fun bookReservation() {
        val intent = Intent(context, ReservationActivity::class.java)
        intent.putExtra("roomId", roomId)
        context.startActivity(intent)
    }
}
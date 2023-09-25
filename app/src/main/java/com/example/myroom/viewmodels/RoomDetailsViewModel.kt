package com.example.myroom.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroom.database.dataclasses.MeetingRoom
import com.example.myroom.database.repositories.MeetingRoomRepository
import kotlinx.coroutines.launch

class RoomDetailsViewModel(private val meetingRoomRepository: MeetingRoomRepository, private val roomId: Int) : ViewModel() {

    val roomInfoLiveData = MutableLiveData<MeetingRoom?>()

    init {
        viewModelScope.launch {
            val roomInfo = meetingRoomRepository.getMeetingRoomById(roomId)
            roomInfoLiveData.postValue(roomInfo)
        }
    }

    fun bookReservation(/* параметры бронирования */) {
        // Реализация бронирования
    }
}
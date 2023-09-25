package com.example.myroom.modelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myroom.database.repositories.MeetingRoomRepository
import com.example.myroom.viewmodels.RoomDetailsViewModel

class RoomDetailsViewModelFactory(private val repository: MeetingRoomRepository, private val roomId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomDetailsViewModel::class.java)) {
            return RoomDetailsViewModel(repository, roomId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
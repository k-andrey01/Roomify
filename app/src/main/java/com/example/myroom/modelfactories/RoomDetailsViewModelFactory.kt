package com.example.myroom.modelfactories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myroom.database.repositories.MeetingRoomRepository
import com.example.myroom.viewmodels.RoomDetailsViewModel

class RoomDetailsViewModelFactory(private val repository: MeetingRoomRepository, private val roomId: Int, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomDetailsViewModel::class.java)) {
            return RoomDetailsViewModel(repository, roomId, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.myroom.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroom.database.dataclasses.MeetingRoom
import com.example.myroom.database.repositories.MeetingRoomRepository
import kotlinx.coroutines.launch

class ListViewModel(private val repository: MeetingRoomRepository) : ViewModel() {

    private val _meetingRooms = MutableLiveData<List<MeetingRoom>>()
    val meetingRooms: LiveData<List<MeetingRoom>> = _meetingRooms

    fun initializeData(){
        viewModelScope.launch {
            repository.insertMeetingRoom(MeetingRoom(roomName = "Room 1", numberOfChairs = 10, hasProjector = true, hasWhiteboard = false, roomDescription = "Room"))
            repository.insertMeetingRoom(MeetingRoom(roomName = "Room 2", numberOfChairs = 8, hasProjector = false, hasWhiteboard = true, roomDescription = "Roomer"))
        }
    }

    fun loadMeetingRooms() {
        viewModelScope.launch {
            _meetingRooms.value = repository.getAllMeetingRooms()
        }
    }
}

package com.example.myroom

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myroom.database.DatabaseManager
import com.example.myroom.database.repositories.MeetingRoomRepository
import com.example.myroom.modelfactories.RoomDetailsViewModelFactory
import com.example.myroom.viewmodels.RoomDetailsViewModel

class RoomDetailsActivity : AppCompatActivity() {
    private lateinit var viewModel: RoomDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_details)

        val meetingRoomDao = DatabaseManager.getDatabase(this).meetingRoomDao()
        val meetingRoomRepository = MeetingRoomRepository(meetingRoomDao)

        val roomId = intent.getIntExtra("roomId", -1)

        viewModel = ViewModelProvider(this, RoomDetailsViewModelFactory(meetingRoomRepository, roomId, this)).get(RoomDetailsViewModel::class.java)

        viewModel.roomInfoLiveData.observe(this, { roomInfo ->
            if (roomInfo != null) {
                val roomNameTextView = findViewById<TextView>(R.id.roomName)
                val chairsTextView = findViewById<TextView>(R.id.chairs)
                val hasProjector = findViewById<TextView>(R.id.projector)
                val hasWhiteBoard = findViewById<TextView>(R.id.whiteboard)
                val description = findViewById<TextView>(R.id.description)

                roomNameTextView.text = "Комната: ${roomInfo.roomName}"
                chairsTextView.text = "Количество кресел: ${roomInfo.numberOfChairs}"
                hasProjector.text = "Проектор: ${if (roomInfo.hasProjector) "Да" else "Нет"}"
                hasWhiteBoard.text = "Доска: ${if (roomInfo.hasWhiteboard) "Да" else "Нет"}"
                description.text = "Описание: ${roomInfo.roomDescription}"
            }
        })

        val bookButton = findViewById<Button>(R.id.bookButton)
        bookButton.setOnClickListener {
            viewModel.bookReservation()
        }
    }
}

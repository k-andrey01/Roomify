package com.example.myroom.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myroom.R
import com.example.myroom.database.DatabaseManager
import com.example.myroom.database.repositories.MeetingRoomRepository
import com.example.myroom.database.repositories.ReservationRepository
import com.example.myroom.modelfactories.RoomDetailsViewModelFactory
import com.example.myroom.viewmodels.RoomDetailsViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class RoomDetailsActivity : AppCompatActivity() {
    private lateinit var viewModel: RoomDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_details)

        val meetingRoomDao = DatabaseManager.getDatabase(this).meetingRoomDao()
        val meetingRoomRepository = MeetingRoomRepository(meetingRoomDao)

        val reservationDao = DatabaseManager.getDatabase(this).reservationDao()
        val reservationRepository = ReservationRepository(reservationDao)

        val roomId = intent.getIntExtra("roomId", -1)

        viewModel = ViewModelProvider(this, RoomDetailsViewModelFactory(meetingRoomRepository, reservationRepository, roomId, this)).get(RoomDetailsViewModel::class.java)

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

        val dateTimeFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        viewModel.reservationsLiveData.observe(this, {reservationData ->
            tableLayout.removeAllViews()

            if (reservationData != null) {
                for (reservation in reservationData) {
                    val row = TableRow(this)

                    val startTimeTextView = TextView(this)
                    startTimeTextView.text = "${dateTimeFormat.format(reservation.startTime)} - "
                    val endTimeTextView = TextView(this)
                    endTimeTextView.text = "${dateTimeFormat.format(reservation.endTime)}"
                    val eventNameTextView = TextView(this)
                    eventNameTextView.text = "   ${reservation.event}"

                    row.addView(startTimeTextView)
                    row.addView(endTimeTextView)
                    row.addView(eventNameTextView)

                    tableLayout.addView(row)
                }
            }
        })
    }
}

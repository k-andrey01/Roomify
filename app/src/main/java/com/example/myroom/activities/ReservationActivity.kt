package com.example.myroom.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.myroom.R
import com.example.myroom.database.DatabaseManager
import com.example.myroom.database.dataclasses.Reservation
import com.example.myroom.database.repositories.ReservationRepository
import com.example.myroom.modelfactories.ReservationViewModelFactory
import com.example.myroom.viewmodels.ReservationViewModel
import java.util.Calendar

class ReservationActivity : AppCompatActivity() {
    private lateinit var viewModel: ReservationViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val reservationDao = DatabaseManager.getDatabase(this).reservationDao()
        val reservationRepository = ReservationRepository(reservationDao)

        viewModel = ViewModelProvider(this, ReservationViewModelFactory(reservationRepository, this)).get(ReservationViewModel::class.java)

        val startTimePicker = findViewById<TimePicker>(R.id.startTimePicker)
        val endTimePicker = findViewById<TimePicker>(R.id.endTimePicker)

        startTimePicker.setIs24HourView(true)
        endTimePicker.setIs24HourView(true)

        val reserveButton = findViewById<Button>(R.id.reserveButton)
        reserveButton.setOnClickListener {
            onReserveButtonClick()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun onReserveButtonClick() {
        val datePicker = findViewById<DatePicker>(R.id.datePicker)
        val startTimePicker = findViewById<TimePicker>(R.id.startTimePicker)
        val endTimePicker = findViewById<TimePicker>(R.id.endTimePicker)
        val eventNameEditText = findViewById<EditText>(R.id.eventNameEditText)

        val calendar = Calendar.getInstance()

        calendar.set(Calendar.YEAR, datePicker.year)
        calendar.set(Calendar.MONTH, datePicker.month)
        calendar.set(Calendar.DAY_OF_MONTH, datePicker.dayOfMonth)
        val selectedDate = calendar.time

        calendar.set(Calendar.HOUR_OF_DAY, startTimePicker.hour)
        calendar.set(Calendar.MINUTE, startTimePicker.minute)
        val startTime = calendar.time

        calendar.set(Calendar.HOUR_OF_DAY, endTimePicker.hour)
        calendar.set(Calendar.MINUTE, endTimePicker.minute)
        val endTime = calendar.time
        val eventName = eventNameEditText.text.toString()

        viewModel.insert(Reservation(intent.getIntExtra("roomId", -1), selectedDate, startTime, endTime, eventName))
    }
}

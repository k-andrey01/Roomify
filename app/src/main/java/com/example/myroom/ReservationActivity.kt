package com.example.myroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker

class ReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val startTimePicker = findViewById<TimePicker>(R.id.startTimePicker)
        val endTimePicker = findViewById<TimePicker>(R.id.endTimePicker)

        startTimePicker.setIs24HourView(true)
        endTimePicker.setIs24HourView(true)
    }
}
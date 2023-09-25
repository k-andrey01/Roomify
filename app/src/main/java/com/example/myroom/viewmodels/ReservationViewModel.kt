package com.example.myroom.viewmodels

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroom.database.dataclasses.Reservation
import com.example.myroom.database.repositories.ReservationRepository
import kotlinx.coroutines.launch

class ReservationViewModel(private val reservationRepository: ReservationRepository, private val context: Context) : ViewModel() {
    fun insert(reservation: Reservation) {
        viewModelScope.launch {
            reservationRepository.insertReservation(reservation)
        }
        (context as Activity).finish()
    }
}
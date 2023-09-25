package com.example.myroom.modelfactories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myroom.database.repositories.ReservationRepository
import com.example.myroom.viewmodels.ReservationViewModel

class ReservationViewModelFactory(private val reservationRepository: ReservationRepository, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReservationViewModel::class.java)) {
            return ReservationViewModel(reservationRepository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
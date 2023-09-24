package com.example.myroom.modelfactories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myroom.database.repositories.CurrentUserRepository
import com.example.myroom.viewmodels.MainViewModel

class MainViewModelFactory(private val currentUserRepository: CurrentUserRepository, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(currentUserRepository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.myroom.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroom.database.repositories.CurrentUserRepository
import kotlinx.coroutines.launch

class MainViewModel(private val currentUserRepository: CurrentUserRepository, private val context: Context) : ViewModel()  {
    private val _hasCurrentUser = MutableLiveData<Boolean>()
    val hasCurrentUser: LiveData<Boolean> = _hasCurrentUser

    init {
        viewModelScope.launch {
            val currentUser = currentUserRepository.getCurrentUser()
            _hasCurrentUser.value = currentUser != null
        }
    }
}
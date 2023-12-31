package com.example.myroom.viewmodels

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myroom.database.repositories.UserRepository
import androidx.lifecycle.viewModelScope
import com.example.myroom.activities.ListActivity
import com.example.myroom.database.dataclasses.CurrentUser
import com.example.myroom.database.dataclasses.User
import com.example.myroom.database.repositories.CurrentUserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository, private val currentUserRepository: CurrentUserRepository, private val context: Context) : ViewModel() {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val isLoginEnabled = MediatorLiveData<Boolean>()

    private val _hasCurrentUser = MutableLiveData<Boolean>()
    val hasCurrentUser: LiveData<Boolean> = _hasCurrentUser


    init {
        isLoginEnabled.addSource(username) { validateLoginFields() }
        isLoginEnabled.addSource(password) { validateLoginFields() }
    }

    private fun validateLoginFields() {
        val usernameValue = username.value.orEmpty()
        val passwordValue = password.value.orEmpty()
        isLoginEnabled.value = usernameValue.isNotEmpty() && passwordValue.isNotEmpty()
    }

    fun initializeData(){
        viewModelScope.launch {
            userRepository.insertUser(User(username = "user1", password = "password1"))
            userRepository.insertUser(User(username = "user2", password = "password2"))
        }
    }

    fun login() {
        val usernameValue = username.value.orEmpty()
        val passwordValue = password.value.orEmpty()

        viewModelScope.launch {
            val isLoginSuccessful = userRepository.loginUser(usernameValue, passwordValue)
            if (isLoginSuccessful) {
                val currentUser = CurrentUser(usernameValue)
                currentUserRepository.insertCurrentUser(currentUser)

                val intent = Intent(context, ListActivity::class.java)
                context.startActivity(intent)
            } else {
                context?.let {
                    Toast.makeText(it, "Указан неверный логин и/или пароль", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

package com.example.myroom

import LoginViewModelFactory
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.myroom.database.DatabaseManager
import com.example.myroom.database.dataclasses.User
import com.example.myroom.database.repositories.CurrentUserRepository
import com.example.myroom.database.repositories.UserRepository
import com.example.myroom.viewmodels.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userDao = DatabaseManager.getDatabase(this).userDao()
        val userRepository = UserRepository(userDao)
        val currentUserDao = DatabaseManager.getDatabase(this).currentUserDao()
        val currentUserRepository = CurrentUserRepository(currentUserDao)

        viewModel = ViewModelProvider(this, LoginViewModelFactory(userRepository, currentUserRepository, this)).get(LoginViewModel::class.java)
        viewModel.initializeData()

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login)

        usernameEditText.addTextChangedListener { text ->
            viewModel.username.value = text.toString()
        }

        passwordEditText.addTextChangedListener { text ->
            viewModel.password.value = text.toString()
        }

        loginButton.setOnClickListener {
            viewModel.login()
        }
    }
}

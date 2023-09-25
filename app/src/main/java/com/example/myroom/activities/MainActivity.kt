package com.example.myroom.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myroom.R
import com.example.myroom.database.DatabaseManager
import com.example.myroom.database.repositories.CurrentUserRepository
import com.example.myroom.modelfactories.MainViewModelFactory
import com.example.myroom.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentUserDao = DatabaseManager.getDatabase(this).currentUserDao()
        val currentUserRepository = CurrentUserRepository(currentUserDao)
        viewModel = ViewModelProvider(this, MainViewModelFactory(currentUserRepository, this)).get(
            MainViewModel::class.java)

        viewModel.hasCurrentUser.observe(this, { hasCurrentUser ->
            val intent = if (hasCurrentUser) {
                Intent(this, ListActivity::class.java)
            } else {
                Intent(this, LoginActivity::class.java)
            }

            startActivity(intent)
            finish()
        })
    }
}
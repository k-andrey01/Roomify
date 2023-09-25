package com.example.myroom.activities

import com.example.myroom.helpclasses.MeetingRoomAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myroom.database.DatabaseManager
import com.example.myroom.database.repositories.MeetingRoomRepository
import com.example.myroom.modelfactories.ListViewModelFactory
import com.example.myroom.viewmodels.ListViewModel
import android.content.Intent
import com.example.myroom.R

class ListActivity : AppCompatActivity() {
    private lateinit var viewModel: ListViewModel
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val meetingRoomDao = DatabaseManager.getDatabase(this).meetingRoomDao()
        val repository = MeetingRoomRepository(meetingRoomDao)

        viewModel = ViewModelProvider(this, ListViewModelFactory(repository)).get(ListViewModel::class.java)
        viewModel.initializeData()

        recyclerView = findViewById(R.id.roomsList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MeetingRoomAdapter { room ->
            val intent = Intent(this, RoomDetailsActivity::class.java)
            intent.putExtra("roomId", room.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        viewModel.meetingRooms.observe(this, { meetingRooms ->
            adapter.submitList(meetingRooms)
        })
    }
}

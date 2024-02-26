package com.example.notanotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notanotes.database.noteDatabase
import com.example.notanotes.repository.NoteRepository
import com.example.notanotes.viewmodel.NoteViewModel
import com.example.notanotes.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {


    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }

    private fun setupViewModel(){
        val noteRepository = NoteRepository(noteDatabase(this))
        val viewModelProviderFactory= NoteViewModelFactory(application,noteRepository)
        noteViewModel= ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]
    }
}
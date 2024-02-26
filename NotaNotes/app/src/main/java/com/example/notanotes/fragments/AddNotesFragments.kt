package com.example.notanotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.notanotes.MainActivity
import com.example.notanotes.R
import com.example.notanotes.databinding.FragmentAddNotesBinding
import com.example.notanotes.databinding.FragmentEditNotesBinding
import com.example.notanotes.model.Note
import com.example.notanotes.viewmodel.NoteViewModel


class AddNotesFragments : Fragment(R.layout.fragment_add_notes) ,MenuProvider {

    private var addNoteBinding : FragmentAddNotesBinding? = null
    private val binding get() = addNoteBinding!!

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var addNotesView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addNoteBinding = FragmentAddNotesBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this , viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel = (activity as MainActivity).noteViewModel
        addNotesView = view

    }

    private fun saveNote(view: View){
        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteDesc = binding.addNoteDesc.text.toString().trim()

        if(noteTitle.isNotEmpty()) {
            val note = Note(0, noteTitle, noteDesc)
            notesViewModel.addNote(note)

            Toast.makeText(addNotesView.context, "Note Saved Successfuly", Toast.LENGTH_SHORT)
                .show()
            view.findNavController().popBackStack(R.id.homeFragments, false)
        }else{
            Toast.makeText(addNotesView.context, "Please enter note title", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.add_notes_menu , menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
      return when(menuItem.itemId){
          R.id.saveMenu -> {
              saveNote(addNotesView)
              true
          }
          else -> false
      }
    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding =null
    }


}
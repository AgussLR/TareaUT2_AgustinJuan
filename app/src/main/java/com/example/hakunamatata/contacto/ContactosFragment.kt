package com.example.hakunamatata.contacto

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hakunamatata.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactosFragment : Fragment() {

    companion object {
        fun newInstance() = ContactosFragment()
    }

    private val viewModel: ContactoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.contactos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab: FloatingActionButton = view.findViewById(R.id.floatingButtonContactos)
        fab.setOnClickListener {
            // Navega al fragmento de detalles de mascota
            findNavController().navigate(R.id.action_ContactosFragment_to_DetallesContactosFragment)
        }
    }
}
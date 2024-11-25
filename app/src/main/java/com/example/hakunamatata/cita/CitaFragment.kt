package com.example.hakunamatata.cita

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hakunamatata.R
import com.example.hakunamatata.databinding.CitasBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CitaFragment : Fragment() {

    private lateinit var binding: CitasBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CitasBinding.inflate(inflater, container, false)

        // Configurar RecyclerView
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab: FloatingActionButton = view.findViewById(R.id.floatingButtonCitas)
        fab.setOnClickListener {
            // Navega al fragmento de detalles de mascota
            findNavController().navigate(R.id.action_CitaFragment_to_detallesCitaFragment)
        }
    }

    private fun setupRecyclerView() {
        // Configurar el RecyclerView para mostrar la lista de citas
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.recyclerView.setOnClickListener{
            Toast.makeText(requireContext(),"Hola  mundooooooo",Toast.LENGTH_SHORT).show()
        }
        // Datos de prueba para el RecyclerView
        val items = listOf(
            CitaData("", "Mascota 1", ""),
            CitaData("", "Mascota 2", "")
        )

        // Asignar el adaptador al RecyclerView
        binding.recyclerView.adapter = RecyclerViewAdapter(items)
    }
}
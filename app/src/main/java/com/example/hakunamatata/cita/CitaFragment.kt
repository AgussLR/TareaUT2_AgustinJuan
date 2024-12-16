package com.example.hakunamatata.cita

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hakunamatata.R
import com.example.hakunamatata.databinding.CitasBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class CitaFragment : Fragment() {

    private lateinit var binding: CitasBinding

    //Firebase.
    val db = Firebase.firestore

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

        //val fab: FloatingActionButton = view.findViewById(R.id.floatingButtonCitas)
        binding.floatingButtonCitas.setOnClickListener {
            // Navega al fragmento de detalles de mascota
            findNavController().navigate(R.id.action_CitaFragment_to_AddCitaFragment)
        }
    }

    private fun setupRecyclerView() {
        // Datos de prueba para el RecyclerView
        val items = listOf(
            CitaData("", "Cita 1", ""),
            CitaData("", "Cita 2", "")
        )

        // Configurar el RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = RecyclerViewAdapter(items) { citaData ->
            // Crear un bundle para pasar datos al fragmento destino
            val bundle = Bundle().apply {
                putString("citaText", citaData.textMascota)
            }

            // Navegar al fragmento "Añadir Citas" pasando el bundle
            findNavController().navigate(R.id.action_CitaFragment_to_AddCitaFragment, bundle)
        }
    }

}
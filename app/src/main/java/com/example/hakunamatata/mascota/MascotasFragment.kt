package com.example.hakunamatata.mascota

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hakunamatata.DetallesMascFragment
import com.example.hakunamatata.R
import com.example.hakunamatata.cita.CitaData
import com.example.hakunamatata.cita.RecyclerViewAdapter
import com.example.hakunamatata.databinding.MascotasBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MascotasFragment : Fragment() {

    private lateinit var binding: MascotasBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MascotasBinding.inflate(inflater, container, false)

        // Configurar RecyclerView
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab: FloatingActionButton = view.findViewById(R.id.floatingButtonMascota)
        fab.setOnClickListener {
            // Navega al fragmento de detalles de mascota
            findNavController().navigate(R.id.action_mascotasFragment_to_detallesMascFragment)
        }
    }

    private fun setupRecyclerView() {
        // Datos de prueba para el RecyclerView
        val items = listOf(
            MascotaData("", "Mascota 1", ""),
            MascotaData("", "Mascota 2", "")
        )

        // Configurar el RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = RecyclerMascViewAdapter(items) { MascotaData ->
            // Crear un bundle para pasar datos al fragmento destino
            val bundle = Bundle().apply {
                putString("mascotaText", MascotaData.textNombreMascota)
            }

            // Navegar al fragmento "Añadir Citas" pasando el bundle
            findNavController().navigate(R.id.action_mascotasFragment_to_detallesMascFragment, bundle)
        }
    }

}
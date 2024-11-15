package com.example.hakunamatata.mascota

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hakunamatata.databinding.MascotasBinding

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


    private fun setupRecyclerView() {
        // Configurar el RecyclerView para mostrar la lista de mascotas
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Datos de prueba para el RecyclerView
        val items = listOf(
            MascotaData("", "Mascota 1", ""),
            MascotaData("", "Mascota 2", "")
        )

        // Asignar el adaptador al RecyclerView
        binding.recyclerView.adapter = RecyclerMascViewAdapter(items)
    }
}
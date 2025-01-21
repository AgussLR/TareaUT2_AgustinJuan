package com.example.hakunamatata.cita

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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
    private lateinit var adapter: RecyclerViewAdapter
    private val listaCitas = mutableListOf<CitaData>() // Lista mutable para manejar citas

    // Firebase Firestore
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CitasBinding.inflate(inflater, container, false)

        // Configurar RecyclerView
        setupRecyclerView()

        // Escuchar cambios en Firebase
        escucharCambiosFirebase()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Manejar clic del FloatingActionButton
        binding.floatingButtonCitas.setOnClickListener {
            findNavController().navigate(R.id.action_CitaFragment_to_AddCitaFragment)
        }
    }

    private fun setupRecyclerView() {
        adapter = RecyclerViewAdapter(listaCitas) { citaData ->
            // Manejar clics en elementos del RecyclerView si es necesario
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun escucharCambiosFirebase() {
        db.collection("citas")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(TAG, "Error al escuchar cambios en Firebase", e)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    listaCitas.clear()
                    for (document in snapshot.documents) {
                        val cita = document.toObject(CitaData::class.java)
                        if (cita != null) {
                            listaCitas.add(cita)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            }
    }
}
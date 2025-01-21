package com.example.hakunamatata.cita

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hakunamatata.databinding.CitaAddBinding
import com.example.hakunamatata.mascota.DetallesMascData
import com.example.hakunamatata.perfil.PerfilData
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class AddCitaFragment: Fragment() {

    private lateinit var binding: CitaAddBinding

    // Firebase Firestore
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CitaAddBinding.inflate(inflater, container, false)

        // DatePicker
        binding.sphorario.setOnClickListener { showDatePickerDialog() }

        // Botón Guardar Cita
        binding.btnGuardarCita.setOnClickListener {
            addDetallesCita()
        }

        return binding.root
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(parentFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.sphorario.setText("$day/$month/$year")
    }

    private fun addDetallesCita() {
        val dni = binding.etdni.text.toString()

        if (dni.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, introduce un número de DNI", Toast.LENGTH_SHORT).show()
            return
        }

        // Verificar si ya existe una cita con el DNI
        db.collection("citas")
            .whereEqualTo("dni", dni)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    // DNI duplicado
                    Toast.makeText(requireContext(), "Ya existe una cita con este DNI", Toast.LENGTH_SHORT).show()
                } else {
                    // Crear nueva cita
                    createCita(dni)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error verificando el DNI.", exception)
                Toast.makeText(requireContext(), "Error al comprobar el DNI", Toast.LENGTH_SHORT).show()
            }
    }

    private fun createCita(dni: String) {
        val cita = citaDetallesData(
            dni,
            binding.etnombre.text.toString(),
            binding.etapellido.text.toString(),
            binding.etnombreemascota.text.toString(),
            binding.ettelefono.text.toString(),
            binding.sphorario.text.toString()
        )

        db.collection("citas")
            .add(cita)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Cita creada exitosamente", Toast.LENGTH_SHORT).show()
                regresarAFragmentoPrincipal()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error añadiendo la cita.", e)
                Toast.makeText(requireContext(), "Error al crear la cita", Toast.LENGTH_SHORT).show()
            }
    }

    private fun regresarAFragmentoPrincipal() {
        // Regresar al fragmento principal después de guardar
        requireActivity().onBackPressed()
    }
}
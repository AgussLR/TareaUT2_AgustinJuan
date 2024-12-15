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

    //Firebase.
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        readCitaDetalles()

        binding = CitaAddBinding.inflate(inflater,container,false)

        //DatePicker.
        binding.sphorario.setOnClickListener{showDatePickerDialog()}

        binding.btnGuardarCita.setOnClickListener{
            addDetallesCita()
        }

        return binding.root

    }

    private fun showDatePickerDialog(){
        val datePicker = DatePickerFragment{day,month,year -> onDateSelected(day,month,year)}
        datePicker.show(parentFragmentManager,"datePicker")
    }

    private fun onDateSelected(day:Int,month:Int,year:Int){
        binding.sphorario.setText("$day/$month/$year")
    }


    private fun addDetallesCita() {
        val dni = binding.etdni.text.toString()

        if (dni.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, introduce un número de dni", Toast.LENGTH_SHORT).show()
            return
        }

        db.collection("citas")
            .whereEqualTo("dni", dni)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    // El microchip ya existe
                    Toast.makeText(requireContext(), "Ya existe una cita con este dni", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Cita con dni $dni ya existe")
                } else {
                    // El microchip no existe, proceder a crear la mascota
                    createCita(dni)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error verificando el dni.", exception)
                Toast.makeText(requireContext(), "Error al comprobar el dni", Toast.LENGTH_SHORT).show()
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
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Documento creado con ID: ${documentReference.id}")
                Toast.makeText(requireContext(), "Cita creada exitosamente", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error añadiendo la cita.", e)
            }
    }


    private fun readCitaDetalles() {

        db.collection("citas")
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    val citas = result.toObjects(citaDetallesData::class.java)
                    if (citas.isNotEmpty()) {
                        // Mostrar los datos de la primera cita
                        val cita = citas[1] // Puedes manejar más de uno en una lista
                        binding.etdni.setText(cita.dni)
                        binding.etnombre.setText(cita.nombre)
                        binding.etapellido.setText(cita.apellidos)
                        binding.etnombreemascota.setText(cita.nombreMascota)
                        binding.ettelefono.setText(cita.telefono)
                        binding.sphorario.setText(cita.fechaCita)

                    }

                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al cargar las citas", exception)
                Toast.makeText(requireContext(), "Error al cargar las citas", Toast.LENGTH_SHORT)
                    .show()
            }

    }

}
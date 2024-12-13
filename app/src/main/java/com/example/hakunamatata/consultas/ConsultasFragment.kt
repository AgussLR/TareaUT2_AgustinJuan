package com.example.hakunamatata.consultas

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hakunamatata.R
import com.example.hakunamatata.databinding.ConsultasBinding
import com.example.hakunamatata.databinding.DetallesMascotaBinding

class ConsultasFragment : Fragment() {
    private lateinit var binding: ConsultasBinding
    companion object {
        fun newInstance() = ConsultasFragment()
    }

    private val viewModel: ConsultasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ConsultasBinding.inflate(inflater, container, false)

        binding.enviarBoton.setOnClickListener {
            Toast.makeText(requireContext(), "Consulta guardada", Toast.LENGTH_SHORT).show()
            enviarConsulta()
        }
        return binding.root
    }

    private fun enviarConsulta() {
        val dni = binding.inputDNI.text.toString()
        val nombre = binding.inputNombre.text.toString()
        val telefono = binding.inputTelefono.text.toString()
        val consulta = binding.inputDescripcion.text.toString()
        val email = binding.inputCorreo.text.toString()

        if (dni.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || consulta.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }


        val subject = "Consulta de $nombre con Dni: $dni"
        val message = """
            Nombre: $nombre
            Teléfono: $telefono
            Consulta: $consulta
        """.trimIndent()

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // Solo aplicaciones de correo
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        // Verifica si hay una app de correo disponible
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "No hay una aplicación de correo instalada", Toast.LENGTH_SHORT).show()
        }
    }
}
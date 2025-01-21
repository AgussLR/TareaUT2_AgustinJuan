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
            enviarConsulta()
            Toast.makeText(requireContext(), "Consulta guardada", Toast.LENGTH_SHORT).show()
        }
        binding.videoView.setOnClickListener {
            insertVideo()
        }
        return binding.root
    }

    //Método para enviar una consulta.
    private fun enviarConsulta() {
        val dni = binding.inputDNI.text.toString()
        val nombre = binding.inputNombre.text.toString()
        val telefono = binding.inputTelefono.text.toString()
        val consulta = binding.inputDescripcion.text.toString()
        val email = binding.inputCorreo.text.toString()

        if (dni.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || consulta.isEmpty() || email.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Por favor, completa todos los campos",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Verificar si el email tiene un formato válido
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(
                requireContext(),
                "Por favor, ingresa un correo válido",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val subject = "Consulta de $nombre con DNI: $dni"
        val message = """
        Nombre: $nombre
        Teléfono: $telefono
        Consulta: $consulta
    """.trimIndent()

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822" // MIME para aplicaciones de correo
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        // Intent explícito para aplicaciones de correo
        val chooser = Intent.createChooser(intent, "Selecciona una aplicación de correo")
        try {
            startActivity(chooser)
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "No hay una aplicación de correo instalada",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    //Método para insertar un vídeo.
    private fun insertVideo() {
        binding.videoView.setVideoPath("android.resource://com.example.hakunamatata/${R.raw.caballos_corriendo}")
        binding.videoView.start()
    }


}
package com.example.hakunamatata.mascota

import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.hakunamatata.R
import com.example.hakunamatata.databinding.DetallesMascotaBinding
import com.example.hakunamatata.perfil.PerfilData
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class DetallesMascFragment : Fragment() {

    private lateinit var binding: DetallesMascotaBinding
    private val imageFileName = "detallesMasc_image.jpg"
    private var selectedImageUri: Uri? = null

    //Firebase.
    val db = Firebase.firestore

    // Código de solicitud de permiso
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            // Permiso concedido, abrir el selector de imagen
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        } else {
            // Permiso no concedido, notificar al usuario
            Toast.makeText(requireContext(), "Permiso denegado para acceder a la galería", Toast.LENGTH_SHORT).show()
        }
    }

    //Opcion de elegir la imagen.
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            selectedImageUri = uri
            binding.imagePerro.setImageURI(uri)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DetallesMascotaBinding.inflate(inflater, container, false)

        // Restaurar la imagen guardada, si existe
        loadImageFromFile()?.let { uri ->
            binding.imagePerro.setImageURI(uri)
        }

        // Listener para la imagen de perfil
        binding.imagePerro.setOnClickListener{
            if (checkPermission()) {
                // Si el permiso ya está concedido, abre el selector de imagen
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
                // Si el permiso no está concedido, solicitar el permiso
                requestPermissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
            }
        }

        // Configurar el listener para guardar la imagen al presionar el botón
        binding.btnMascota.setOnClickListener {
            addDetallesMascota()
            selectedImageUri?.let {
                saveImageToFile(it) // Guardar la imagen seleccionada
            } ?: Log.d("PhotoPicker", "No image selected to save")
            Toast.makeText(requireContext(), "Perfil guardado", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    private fun saveImageToFile(uri: Uri) {
        try {
            val inputStream: InputStream? = requireContext().contentResolver.openInputStream(uri)
            val file = File(requireContext().filesDir, imageFileName)

            inputStream?.use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
            }
            Log.d("PhotoPicker", "Image saved successfully")
        } catch (e: Exception) {
            Log.e("PhotoPicker", "Error saving image: ${e.message}")
        }
    }

    private fun loadImageFromFile(): Uri? {
        val file = File(requireContext().filesDir, imageFileName)
        return if (file.exists()) Uri.fromFile(file) else null
    }

    // Verificar si los permisos están concedidos
    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun addDetallesMascota() {
        val microChip = binding.chipMascota.text.toString()

        if (microChip.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, introduce un número de microchip", Toast.LENGTH_SHORT).show()
            return
        }

        db.collection("mascotas")
            .whereEqualTo("microChip", microChip)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    // El microchip ya existe
                    Toast.makeText(requireContext(), "Ya existe una mascota con este microchip", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Mascota con microchip $microChip ya existe")
                } else {
                    // El microchip no existe, proceder a crear la mascota
                    createMascota(microChip)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error verificando el microchip.", exception)
                Toast.makeText(requireContext(), "Error al comprobar el microchip", Toast.LENGTH_SHORT).show()
            }
    }

    private fun createMascota(microChip: String) {
        val edad = binding.edadMascota.text.toString().toIntOrNull()

        if (edad == null) {
            Toast.makeText(requireContext(), "La edad debe ser un número válido", Toast.LENGTH_SHORT).show()
            return
        }

        val mascota = DetallesMascData(
            microChip,
            binding.nombreMascota.text.toString(),
            binding.razaMascota.text.toString(),
            edad,
            binding.colorMascota.text.toString(),
            binding.tipoMascota.text.toString()
        )

        db.collection("mascotas")
            .add(mascota)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Documento creado con ID: ${documentReference.id}")
                Toast.makeText(requireContext(), "Mascota creada exitosamente", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error añadiendo la mascota.", e)
            }
    }

}
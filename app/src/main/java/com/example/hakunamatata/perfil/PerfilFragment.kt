package com.example.hakunamatata.perfil

import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.hakunamatata.databinding.PerfilBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class PerfilFragment : Fragment() {
    private lateinit var binding: PerfilBinding
    private val imageFileName = "profile_image.jpg"
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
            binding.imagenperfil.setImageURI(uri)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    companion object {
        fun newInstance() = PerfilFragment()
    }

    private val viewModel: PerfilViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PerfilBinding.inflate(inflater,container,false)

        // Restaurar la imagen guardada, si existe
        loadImageFromFile()?.let { uri ->
            binding.imagenperfil.setImageURI(uri)
        }

        // Listener para la imagen de perfil
        binding.imagenperfil.setOnClickListener{
            if (checkPermission()) {
                // Si el permiso ya está concedido, abre el selector de imagen
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
                // Si el permiso no está concedido, solicitar el permiso
                requestPermissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
            }
        }


        // Configurar el listener para guardar la imagen al presionar el botón
        binding.btnGuardarPerfil.setOnClickListener {
            addPerfil()
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

    private fun addPerfil() {
        val correo = binding.correo.text.toString()

        // Verificar si el perfil ya existe basado en el correo
        db.collection("perfiles")
            .whereEqualTo("correo", correo) // Buscar por el correo como identificador único
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    // Si existe, actualizamos el documento
                    for (document in result) {
                        val docId = document.id
                        updatePerfil(docId)
                    }
                } else {
                    // Si no existe, lo añadimos como nuevo
                    createPerfil()
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error buscando el documento.", exception)
            }
    }

    private fun createPerfil() {
        val perfil = PerfilData(
            binding.nombre.text.toString(),
            binding.apellidos.text.toString(),
            binding.correo.text.toString(),
            binding.telefono.text.toString()
        )

        db.collection("perfiles")
            .add(perfil)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Documento creado con ID: ${documentReference.id}")
                Toast.makeText(requireContext(), "Perfil creado exitosamente", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error añadiendo el documento.", e)
            }
    }

    private fun updatePerfil(docId: String) {
        val updatedData = mapOf(
            "nombre" to binding.nombre.text.toString(),
            "apellidos" to binding.apellidos.text.toString(),
            "correo" to binding.correo.text.toString(),
            "telefono" to binding.telefono.text.toString()
        )

        db.collection("perfiles")
            .document(docId)
            .update(updatedData)
            .addOnSuccessListener {
                Log.d(TAG, "Documento actualizado correctamente")
                Toast.makeText(requireContext(), "Perfil actualizado exitosamente", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error actualizando el documento.", e)
            }
    }

}
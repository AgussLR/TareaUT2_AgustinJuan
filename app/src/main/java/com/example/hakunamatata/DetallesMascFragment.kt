package com.example.hakunamatata

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.hakunamatata.databinding.DetallesMascotaBinding
import java.io.InputStream

class DetallesMascFragment : Fragment() {
    private lateinit var binding: DetallesMascotaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño para este fragmento

        binding = DetallesMascotaBinding.inflate(inflater, container, false)

        // Botón para cambiar la imagen
        binding.imagePerro.setOnClickListener {
            selectImageFromGallery()
        }

        return binding.root
    }

    private fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        galleryLauncher.launch(intent)
    }

    // Launcher para manejar el resultado
    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri: Uri? = result.data?.data
                if (imageUri != null) {
                    updateProfileImage(imageUri)
                }
            }
        }

    private fun updateProfileImage(imageUri: Uri) {
        // Mostrar la imagen seleccionada en el ImageView
        val inputStream: InputStream? = requireContext().contentResolver.openInputStream(imageUri)
        val selectedImage = BitmapFactory.decodeStream(inputStream)
        //binding.ivProfileImage.setImageBitmap(selectedImage)
    }
}
package com.example.hakunamatata

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hakunamatata.databinding.PerfilBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var binding: PerfilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadPerfil()
        // Navegar a MascotasFragment al hacer clic en el botón
        binding.mascota.setOnClickListener {
            findNavController().navigate(R.id.mascotasFragment)
        }
    }

    // Método para cargar datos del perfil
    private fun loadPerfil() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getPerfil()  // Llama al endpoint de perfil
                if (response.isSuccessful) {
                    response.body()?.let { PerfilResponse ->
                        // Asigna directamente los valores del perfil a los elementos de la UI
                        binding.apply {
                            binding.nombre.setText(PerfilResponse.nombre)
                            binding.apellidos.setText(PerfilResponse.apellidos)
                            binding.correo.setText(PerfilResponse.correo)
                            binding.telefono.setText(PerfilResponse.telefono)

                            // Carga de la imagen de perfil
                            //Glide.with(this@MainActivity)
                            //.load(PerfilResponse.imagenPerfilUrl)
                            //.into(imagenperfil)
                        }
                    }
                } else {
                    Log.e("API Error", "Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("Network Error", "Exception: $e")
            }
        }
    }

}
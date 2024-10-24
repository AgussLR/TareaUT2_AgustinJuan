package com.example.hakunamatata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hakunamatata.databinding.PerfilBinding

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

        // Navegar a MascotasFragment al hacer clic en el botón
        binding.mascota.setOnClickListener {
            findNavController().navigate(R.id.mascotasFragment)
        }
    }

}
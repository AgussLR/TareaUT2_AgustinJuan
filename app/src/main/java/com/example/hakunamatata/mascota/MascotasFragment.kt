package com.example.hakunamatata.mascota

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hakunamatata.databinding.MascotasBinding

class MascotasFragment : Fragment() {
    private lateinit var binding: MascotasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MascotasBinding.inflate(inflater, container, false)
        return binding.root
    }

}
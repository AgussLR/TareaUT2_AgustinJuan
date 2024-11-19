package com.example.hakunamatata.mascota

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hakunamatata.DetallesMascFragment
import com.example.hakunamatata.R
import com.example.hakunamatata.databinding.MascotasBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddMascotasFragment : Fragment() {
    private lateinit var binding: MascotasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MascotasBinding.inflate(inflater, container, false)
        return binding.root
    }

}
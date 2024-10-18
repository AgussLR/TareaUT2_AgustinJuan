package com.example.hakunamatata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hakunamatata.databinding.ConfiguracionBinding


class Configuracion_fragment: Fragment() {

    private lateinit var binding:ConfiguracionBinding

    override fun onCreateView(

        inflater:LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        binding = ConfiguracionBinding.inflate(inflater, container, false)
        return binding.root
    }

}
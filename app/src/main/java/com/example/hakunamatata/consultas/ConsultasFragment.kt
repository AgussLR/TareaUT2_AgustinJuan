package com.example.hakunamatata.consultas

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

        binding.enviarBoton.setOnClickListener{
            Toast.makeText(requireContext(),"Consulta guardada",Toast.LENGTH_SHORT).show()
        }



        return binding.root
    }
}
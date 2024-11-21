package com.example.hakunamatata

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hakunamatata.databinding.LoginBinding
import com.example.hakunamatata.databinding.PerfilBinding

class LoginFragment: Fragment() {
    private lateinit var binding: LoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el botón de inicio de sesión
        binding.loginButton.setOnClickListener {

            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (username == "admin" && password == "1234") {
                // Acción tras inicio de sesión correcto
                Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT)
                    .show()

                findNavController().navigate(R.id.CitaFragment)
            } else {
                Toast.makeText(requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }



}
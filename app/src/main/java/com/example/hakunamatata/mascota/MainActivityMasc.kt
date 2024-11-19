package com.example.hakunamatata.mascota

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hakunamatata.DetallesMascFragment
import com.example.hakunamatata.databinding.MascotasBinding

class MainActivityMasc : AppCompatActivity() {

    private lateinit var binding: MascotasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = MascotasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Definir el LayoutManager, que en este caso será un LinearLayoutManager para una lista vertical
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            MascotaData("", "Mascota 1", "Perro"),
            MascotaData("", "Mascota 2", "Gato")
        )

        // Asignar el adaptador al RecyclerView
        binding.recyclerView.adapter = RecyclerMascViewAdapter(items)

    }

}
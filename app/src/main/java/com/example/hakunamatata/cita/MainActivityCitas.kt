package com.example.hakunamatata.cita

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hakunamatata.databinding.CitasBinding

class MainActivityCitas : AppCompatActivity() {

    private lateinit var binding: CitasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = CitasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Definir el LayoutManager, que en este caso será un LinearLayoutManager para una lista vertical
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Definir el formato de la fecha
        // val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        // val date: Date = format.parse("2017-09-23 10:15") ?: throw IllegalArgumentException("Invalid date format")
        val items = listOf(
            CitaData("", "Mascota 1", "")

        )
        //date.toString()
        // Asignar el adaptador al RecyclerView
        binding.recyclerView.adapter = RecyclerViewAdapter(items)
    }
}
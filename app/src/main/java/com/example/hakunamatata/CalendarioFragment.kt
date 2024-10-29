package com.example.hakunamatata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hakunamatata.databinding.CalendarioBinding
import com.example.hakunamatata.databinding.CitaAddBinding
import java.text.SimpleDateFormat
import java.util.Locale

class CalendarioFragment: Fragment() {

    private lateinit var binding: CalendarioBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CalendarioBinding.inflate(inflater, container, false)


        // Formato para mostrar la fecha seleccionada
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        // Mostrar la fecha seleccionada en el TextView
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = dateFormat.format(
                SimpleDateFormat(
                    "yyyy/MM/dd",
                    Locale.getDefault()
                ).parse("$year/${month + 1}/$dayOfMonth")!!
            )



        }
        return binding.root
    }
}
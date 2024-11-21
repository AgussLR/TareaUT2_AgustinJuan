package com.example.hakunamatata.calendario

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.example.hakunamatata.databinding.CalendarioBinding
import java.util.Calendar
import java.util.Calendar.getInstance
import com.applandeo.materialcalendarview.EventDay
import com.example.hakunamatata.R


class CalendarioFragment : Fragment() {

    private lateinit var binding: CalendarioBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el layout usando View Binding
        binding = CalendarioBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el calendario
        setupCalendar()
    }

    private fun setupCalendar() {
        val calendarView = binding.calendarView

        // Establecer una fecha mínima
        val minDate = getInstance()
        minDate.add(Calendar.MONTH, -1) // Un mes antes
        calendarView.setMinimumDate(minDate)


        // Establecer la fecha máxima (1000 años después de la fecha actual)
        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.YEAR, 1000) // 1000 años después
        calendarView.setMaximumDate(maxDate)

        // Añadir eventos personalizados
        val events = mutableListOf<EventDay>()
        val today = getInstance()
        events.add(EventDay(today, R.drawable.icono))
        calendarView.setEvents(events)


        // Configurar clics en los días usando OnCalendarDayClickListener correctamente.

        calendarView.setOnCalendarDayClickListener(object : OnCalendarDayClickListener {
             override fun onClick(calendarDay: CalendarDay) {
                // Obtén la fecha seleccionada
                val clickedDate = calendarDay.calendar
                // Muestra un Toast con la fecha seleccionada
                Toast.makeText(
                    context ?: return, // Verifica que el contexto no sea null
                    "Fecha seleccionada: ${clickedDate.time}",
                    Toast.LENGTH_SHORT
                ).show()
                 highlightSelectedDay(calendarDay)
            }


        })
    }

    // Cambiar la apariencia del día seleccionado
    private fun highlightSelectedDay(calendarDay: CalendarDay) {
        val selectedDate = calendarDay.calendar


        // Añadir un evento para el día seleccionado para que tenga un ícono o resalte
        val selectedEvent = EventDay(selectedDate, R.drawable.icono)  // Usa tu ícono aquí
        binding.calendarView.setEvents(listOf(selectedEvent))

        // Cambiar el color de fondo del día seleccionado
        val selectedColor = Color.parseColor("#FF4081") // El color para el fondo (puedes cambiarlo)

        // Cambiar el color de fondo del día seleccionado
        //binding.calendarView.setSelectionColor(selectedColor)// Color de fondo para el día seleccionado (puedes cambiarlo)
    }


}
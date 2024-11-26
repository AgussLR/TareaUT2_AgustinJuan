package com.example.hakunamatata.cita

import android.widget.DatePicker

data class CitaResponse(

    val fecha: DatePicker,          // fecha de la cita.
    val Dni: String,                // dni del dueño
    val nombre: String,             // nombre del dueño
    val apellido: String,           // apellidos del dueño
    val mascota: String,            // mascota
    val telefono: String,           // telefono del dueño
)
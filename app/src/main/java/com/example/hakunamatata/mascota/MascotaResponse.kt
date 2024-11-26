package com.example.hakunamatata.mascota

data class MascotaResponse(

    val microchip: String,          //Microchip del animal o identificador.
    val nombre: String,             // Nombre del animal
    val raza: String,               // raza del animal
    val edad: String,               // edad del animal
    val colormasc: String,          // color de la mascota
    val tipo: String,               // tipo de animal
)
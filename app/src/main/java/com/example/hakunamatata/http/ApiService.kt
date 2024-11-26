package com.example.hakunamatata.http

import com.example.hakunamatata.cita.CitaResponse
import com.example.hakunamatata.mascota.MascotaResponse
import com.example.hakunamatata.perfil.PerfilResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("perfil")
    suspend fun getPerfil(): Response<PerfilResponse>

    @GET("mascota")
    suspend fun getMascota():Response<MascotaResponse>

    @GET("cita")
    suspend fun getCita():Response<CitaResponse>
}


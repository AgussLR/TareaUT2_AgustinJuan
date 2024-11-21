package com.example.hakunamatata.http

import com.example.hakunamatata.perfil.PerfilResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("perfil")
    suspend fun getPerfil(): Response<PerfilResponse>
}


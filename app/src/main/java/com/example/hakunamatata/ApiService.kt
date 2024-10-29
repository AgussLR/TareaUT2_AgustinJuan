package com.example.hakunamatata

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("perfil")
    suspend fun getPerfil(): Response<PerfilResponse>
}


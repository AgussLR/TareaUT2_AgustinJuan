package com.example.hakunamatata

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://5f7b94b0-5a04-4002-a983-29cc8200ba79.mock.pstmn.io"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build()

    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
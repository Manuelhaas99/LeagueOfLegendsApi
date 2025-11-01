package com.example.leagueoflegendsapi.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val  BASE_URL = "https://americas.api.riotgames.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://americas.api.riotgames.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: RiotApiService by lazy {
        retrofit.create(RiotApiService::class.java)
    }
}


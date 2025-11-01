package com.example.leagueoflegendsapi.data.client

import com.example.leagueoflegendsapi.data.RiotApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val  BASE_URL = "https://americas.api.riotgames.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: RiotApiService by lazy {
        retrofit.create(RiotApiService::class.java)
    }
}
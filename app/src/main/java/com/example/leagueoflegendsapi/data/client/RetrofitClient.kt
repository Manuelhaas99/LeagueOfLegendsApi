package com.example.leagueoflegendsapi.data.client

import com.example.leagueoflegendsapi.data.AmericaApiService
import com.example.leagueoflegendsapi.data.LAApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val AMERICA_URL = "https://americas.api.riotgames.com/"
    private const val LA_BASE_URL = "https://la1.api.riotgames.com/"

    val americaClient: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AMERICA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val laClient: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(LA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val americaApi: AmericaApiService by lazy {
        americaClient.create(AmericaApiService::class.java)
    }

    val laApi: LAApiService by lazy {
        laClient.create(LAApiService::class.java)
    }

}
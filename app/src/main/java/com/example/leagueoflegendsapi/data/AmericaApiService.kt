package com.example.leagueoflegendsapi.data

import com.example.leagueoflegendsapi.model.Summoner
import com.example.leagueoflegendsapi.model.SummonerData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface AmericaApiService {

    companion object {
        private const val API_KEY = "RGAPI-83a5a3dc-a73f-4146-b074-7db118027d7b"
    }

    @GET("riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    suspend fun getSummonerByName(
        @Path("gameName") summonerName: String,
        @Path("tagLine") tagLine: String,
        @Header("X-Riot-Token") apiKey: String = API_KEY
    ): Summoner
}

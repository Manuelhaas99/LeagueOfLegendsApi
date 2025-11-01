package com.example.leagueoflegendsapi.data

import com.example.leagueoflegendsapi.model.Summoner
import com.example.leagueoflegendsapi.model.SummonerData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface RiotApiService {

    @GET("lol/match/v5/matches/by-puuid/{puuid}/ids")
    suspend fun getSummonerByPuuid(
        @Path("puuid") puuid: String,
        @Query("api_key") apiKey: String,
    ): SummonerData

    @GET("riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    suspend fun getSummonerByName(
        @Path("gameName") summonerName: String,
        @Path("tagLine") tagLine: String,
        @Header("X-Riot-Token") apiKey: String
    ): Summoner
}



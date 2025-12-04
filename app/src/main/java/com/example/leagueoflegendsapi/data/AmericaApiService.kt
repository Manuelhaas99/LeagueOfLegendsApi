package com.example.leagueoflegendsapi.data

import com.example.leagueoflegendsapi.model.MatchDetail
import com.example.leagueoflegendsapi.model.Summoner
import com.example.leagueoflegendsapi.model.SummonerData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface AmericaApiService {

    companion object {
        private const val API_KEY = "RGAPI-26b1e96a-8e9b-4293-8519-3591d44caaca"
    }

    @GET("riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    suspend fun getSummonerByName(
        @Path("gameName") summonerName: String,
        @Path("tagLine") tagLine: String,
        @Header("X-Riot-Token") apiKey: String = API_KEY
    ): Summoner

    @GET("/lol/match/v5/matches/by-puuid/{puuid}/ids")
    suspend fun getMatchByPuuid(
        @Path("puuid") puuid: String,
        @Query("start") start: Int = 0,
        @Query("count") count: Int = 20,
        @Query("api_key") apiKey: String = API_KEY
    ): List<String>

    @GET("/lol/match/v5/matches/{matchId}")
    suspend fun getMatchByMatchId(
        @Path("matchId") matchId: String,
        @Query("api_key") apiKey: String = API_KEY
    ): MatchDetail
}

package com.example.leagueoflegendsapi.ui.theme.data

import com.example.leagueoflegendsapi.ui.theme.model.RankedInfo
import com.example.leagueoflegendsapi.ui.theme.model.Summoner
import com.example.leagueoflegendsapi.ui.theme.model.SummonerData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


interface RiotApiService {


    @GET("lol/summoner/v4/summoners/by-puuid/{puuid}")
    suspend fun getSummonerByPuuid(
            @Path("puuid")puuid: String,
            @Header("X-Riot-Token") apiKey: String,
    ): SummonerData

    @GET("lol/league/v4/entries/by-summoner/{summonerId}")
    suspend fun getRankedInfo(
        @Path("summonerId") summonerId: String,
        @Header("X-Riot-Token") apiKey: String
    ): List<RankedInfo>

    @GET("riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    suspend fun getSummonerByName(
        @Path("gameName") summonerName: String,
        @Path("tagLine") tagLine: String,
        @Header("X-Riot-Token") apiKey: String
    ): Summoner
}



package com.example.leagueoflegendsapi.data

import com.example.leagueoflegendsapi.model.ChampionFullResponse
import com.example.leagueoflegendsapi.model.RankedData
import com.example.leagueoflegendsapi.model.SummonerData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface LAApiService {

  companion object {
    private const val API_KEY = "RGAPI-26b1e96a-8e9b-4293-8519-3591d44caaca"
  }

  @GET("/lol/summoner/v4/summoners/by-puuid/{puuid}")
  suspend fun getSummonerByPuuid(
    @Path("puuid") puuid: String,
    @Query("api_key") apiKey: String = API_KEY,
  ): SummonerData

  @GET("/lol/league/v4/entries/by-puuid/{puuid}")
  suspend fun getRankedByPuuid(
    @Path("puuid") puuid: String,
    @Query("api_key") apiKey: String = API_KEY
  ): List<RankedData>

  @GET
    suspend fun getChampions(@Url url: String): ChampionFullResponse
}
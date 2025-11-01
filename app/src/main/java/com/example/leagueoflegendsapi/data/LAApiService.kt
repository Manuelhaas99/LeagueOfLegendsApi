package com.example.leagueoflegendsapi.data

import com.example.leagueoflegendsapi.model.SummonerData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LAApiService {

  companion object {
    private const val API_KEY = "RGAPI-83a5a3dc-a73f-4146-b074-7db118027d7b"
  }

  @GET("/lol/summoner/v4/summoners/by-puuid/{puuid}")
  suspend fun getSummonerByPuuid(
    @Path("puuid") puuid: String,
    @Query("api_key") apiKey: String = API_KEY,
  ): SummonerData
}
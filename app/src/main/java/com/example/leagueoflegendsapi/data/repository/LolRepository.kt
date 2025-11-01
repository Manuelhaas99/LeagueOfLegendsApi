package com.example.leagueoflegendsapi.data.repository

import com.example.leagueoflegendsapi.data.RiotApiService
import com.example.leagueoflegendsapi.model.Summoner

class LolRepository(
  private val lolApiService: RiotApiService
): LolRepositoryI {

  companion object {
    private const val API_KEY = "RGAPI-83a5a3dc-a73f-4146-b074-7db118027d7b"
  }

  override suspend fun getAccount(summonerName: String, tagLine: String): Summoner {
    return lolApiService.getSummonerByName(summonerName, tagLine, API_KEY)
  }

  override suspend fun getMatches(): String {
    TODO("Not yet implemented")
  }
}

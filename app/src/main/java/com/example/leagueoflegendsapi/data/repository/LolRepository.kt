package com.example.leagueoflegendsapi.data.repository

import com.example.leagueoflegendsapi.data.AmericaApiService
import com.example.leagueoflegendsapi.data.LAApiService
import com.example.leagueoflegendsapi.model.Summoner
import com.example.leagueoflegendsapi.model.SummonerData

class LolRepository(
  private val lolApiService: AmericaApiService,
  private val laApiService: LAApiService
): LolRepositoryI {



  override suspend fun getAccount(summonerName: String, tagLine: String): Summoner {
    return lolApiService.getSummonerByName(summonerName, tagLine)
  }

  override suspend fun getSummonerData(puuid: String): SummonerData {
    return laApiService.getSummonerByPuuid(puuid = puuid)
  }
}

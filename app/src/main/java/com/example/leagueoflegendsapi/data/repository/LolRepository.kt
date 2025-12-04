package com.example.leagueoflegendsapi.data.repository

import com.example.leagueoflegendsapi.data.AmericaApiService
import com.example.leagueoflegendsapi.data.LAApiService
import com.example.leagueoflegendsapi.model.ChampionFullResponse
import com.example.leagueoflegendsapi.model.MatchDetail
import com.example.leagueoflegendsapi.model.RankedData
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

  override suspend fun getRankedByPuuid(puuid: String): List<RankedData> {
    return laApiService.getRankedByPuuid(puuid = puuid)
  }

  override suspend fun getChampions(url: String): ChampionFullResponse {
    return laApiService.getChampions(url)
  }

  override suspend fun getMatchByPuuid(puuid: String): List<String>{
    return lolApiService.getMatchByPuuid(puuid = puuid)
  }

  override suspend fun getMatchByMatchId(matchId: String): MatchDetail {
    return lolApiService.getMatchByMatchId(matchId = matchId)
  }


}

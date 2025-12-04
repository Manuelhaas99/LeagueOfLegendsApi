package com.example.leagueoflegendsapi.data.repository

import com.example.leagueoflegendsapi.model.ChampionFullResponse
import com.example.leagueoflegendsapi.model.MatchDetail
import com.example.leagueoflegendsapi.model.RankedData
import com.example.leagueoflegendsapi.model.Summoner
import com.example.leagueoflegendsapi.model.SummonerData

// Information of the account
// Matches
// Summoner info
// Ranked info
interface LolRepositoryI {
  suspend fun getAccount(summonerName: String, tagLine: String): Summoner
  suspend fun getSummonerData(puuid: String): SummonerData
  suspend fun getRankedByPuuid(puuid: String): List<RankedData>
  suspend fun getMatchByPuuid(puuid: String): List<String>
  suspend fun getMatchByMatchId(matchId: String): MatchDetail
  suspend fun getChampions(url: String): ChampionFullResponse
}
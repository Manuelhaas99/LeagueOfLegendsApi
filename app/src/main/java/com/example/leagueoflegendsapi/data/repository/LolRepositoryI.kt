package com.example.leagueoflegendsapi.data.repository

import com.example.leagueoflegendsapi.model.Summoner
import com.example.leagueoflegendsapi.model.SummonerData

// Information of the account
// Matches
// Summoner info
// Ranked info
interface LolRepositoryI {
  suspend fun getAccount(summonerName: String, tagLine: String): Summoner
  suspend fun getSummonerData(puuid: String): SummonerData
}
package com.example.leagueoflegendsapi.data.repository

import com.example.leagueoflegendsapi.model.Summoner

// Information of the account
// Matches
// Summoner info
// Ranked info
interface LolRepositoryI {
  suspend fun getAccount(summonerName: String, tagLine: String): Summoner
  suspend fun getMatches(): String
}
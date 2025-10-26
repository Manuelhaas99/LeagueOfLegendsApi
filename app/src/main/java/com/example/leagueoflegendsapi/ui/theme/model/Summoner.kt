package com.example.leagueoflegendsapi.ui.theme.model

data class Summoner(
    val id: String,
    val accountId: String,
    val puuid: String,
    val gameName: String,       // o gameName
    val summonerLevel: Int,
    val tagLine: String?    // si estás usando Riot-ID
)

data class SummonerData(
    val id: String,
    val accountId: String,
    val puuid: String,
    val name: String,
    val summonerLevel: Int
)

data class RankedInfo(
    val queueType: String,
    val tier: String,
    val rank: String,
    val leaguePoints: Int,
    val wins: Int,
    val losses: Int
)

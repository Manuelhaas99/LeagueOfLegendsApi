package com.example.leagueoflegendsapi.model

data class Summoner(
    val id: String,
    val accountId: String,
    val puuid: String,
    val gameName: String,
    val summonerLevel: Long,
    val profileIconId: Int,
    val revisionDate: Long,
    val tagLine: String?
)

data class SummonerData(
    val puuid: String,
    val profileIconId: Int,
    val revisionDate: Long,
    val summonerLevel: Long
)

data class RankedInfo(
    val queueType: String,
    val tier: String,
    val rank: String,
    val leaguePoints: Int,
    val wins: Int,
    val losses: Int
)

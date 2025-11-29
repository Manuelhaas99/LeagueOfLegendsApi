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
    val summonerLevel: Long,
    val gameName: String
)

data class RankedData(
    val leagueId: String,
    val queueType: String,
    val tier: String?,
    val rank: String?,
    val leaguePoints: Int,
    val wins: Int,
    val losses: Int,
    val hotStreak: Boolean,
)

data class MatchDetail(
    val metadata: Metadata,
    val info: Info
)

data class Metadata(
    val matchId: String,
    val participants: List<String>
)

data class Info(
    val gameDuration: Long,
    val gameEndTimestamp: Long? = null,
    val gameStartTimestamp: Long,
    val gameMode: String,
    val queueId: Int,
    val participants: List<Participant>
)

data class Participant(
    val puuid: String,
    val summonerName: String,
    val championName: String,
    val championId: Int,
    val perks: PerksDto,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val win: Boolean,
    val item0: Int?,
    val item1: Int?,
    val item2: Int?,
    val item3: Int?,
    val item4: Int?,
    val item5: Int?,
    val item6: Int?,
    val summoner1Id: Int,
    val summoner2Id: Int,
)

data class PerksDto(
    val styles: List<PerkStyle>
)

data class PerkStyle(
    val description: String,
    val style: Int,
    val selections: List<PerkStyleSelection>
)
data class PerkStyleSelection(
    val perk: Int,
    val var1: Int,
    val var2: Int,
    val var3: Int,
)

data class ChampionFullResponse(
    val data: Map<String, ChampionDto>
)
data class ChampionDto(
    val key: String,
    val id: String,
    val name: String,
    val skins: List<SkinDto>
)

data class SkinDto(
    val id: String,
    val num: Int,
    val name: String,
)


enum class RankedQueue(val apiName: String){
    SOLO_DUO("RANKED_SOLO_5x5"),
    FLEX("RANKED_FLEX_SR")
}

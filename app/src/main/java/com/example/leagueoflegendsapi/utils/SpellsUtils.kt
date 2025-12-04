package com.example.leagueoflegendsapi.utils

fun getSpellNameById(spellId: Int): String {
    return when(spellId){
        1 -> "summoner_boost"
        3 -> "summoner_exhaust"
        4 -> "summoner_flash"
        6 -> "summoner_haste"
        7 -> "summoner_heal"
        32 -> "summoner_mark"
        11 -> "summoner_smite"
        12 -> "summoner_teleport_new"
        21 -> "summonerbarrier"
        14 -> "summonerignite"
        13 -> "summonermana"
        2201 -> "icon_summonerspell_flee.2v2_mode_fighters"
        else -> "summoner_empty"
    }
}

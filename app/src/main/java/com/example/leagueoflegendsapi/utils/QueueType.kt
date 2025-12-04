package com.example.leagueoflegendsapi.utils

fun QueueType(queueId: Int): String {
    return when(queueId){
        420 -> "Ranked Solo/Duo"
        440 -> "Ranked Flex"
        450 -> "ARAM"
        400 -> "Normal Draft"
        430 -> "Blind Pick"
        490 -> "Quick Play"
        1900 -> "URF"
        1700 -> "Arena"
        else -> "Unknown"
    }
}
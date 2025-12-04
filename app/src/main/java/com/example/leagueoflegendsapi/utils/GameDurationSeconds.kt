package com.example.leagueoflegendsapi.utils

import android.annotation.SuppressLint
import kotlin.math.min

@SuppressLint("DefaultLocale")
fun GameDurationSeconds(info: com.example.leagueoflegendsapi.model.Info): String {
    val totalSeconds = if(info.gameEndTimestamp != null){
        info.gameDuration
    } else {
        info.gameDuration / 1000
    }

    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60

    return String.format("%d:%02d", minutes, seconds)
}
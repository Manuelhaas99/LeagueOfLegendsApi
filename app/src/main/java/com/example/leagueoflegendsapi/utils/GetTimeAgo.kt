package com.example.leagueoflegendsapi.utils

import java.sql.Timestamp
import java.util.concurrent.TimeUnit
import android.text.format.DateUtils
import kotlin.math.roundToInt

fun getTimeAgo(timestamp: Long): String {
    if (timestamp == 0L) return "N/A"

    val now = System.currentTimeMillis()
    val diff = now - timestamp

    val hours = TimeUnit.MILLISECONDS.toHours(diff)

    return when {
        diff < TimeUnit.MINUTES.toMillis(1) -> "Justo ahora"

        hours < 1 -> {
            val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
            "Hace $minutes min"
        }

        hours < 24 -> {
            "Hace $hours ${if (hours == 1L) "hora" else "horas"}"
        }

        else -> {
            val days = (diff / (24.0 * 60 * 60 * 1000)).roundToInt()
            if (days <= 1) "Ayer" else "Hace $days días"
        }
    }
}
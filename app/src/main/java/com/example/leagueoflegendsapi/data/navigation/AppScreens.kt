package com.example.leagueoflegendsapi.data.navigation

import kotlinx.serialization.Serializable

object AppScreens {

    @Serializable
    object MainScreen

    @Serializable
    data class MatchInfoScreen(
        val matchId: String
    )
}
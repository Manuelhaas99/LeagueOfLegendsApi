package com.example.leagueoflegendsapi.ui.screens

import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.leagueoflegendsapi.ui.viewModel.RiotViewModel


@Composable
fun MatchInfoScreen(
    riotViewModel: RiotViewModel,
    navController: NavController,
    matchId: String,
) {
    ElevatedCard() {
        Text( text = "MatchInfo card")
    }
}
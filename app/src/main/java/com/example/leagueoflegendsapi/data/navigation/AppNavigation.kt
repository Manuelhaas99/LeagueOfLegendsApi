package com.example.leagueoflegendsapi.data.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.leagueoflegendsapi.model.MatchDetail
import com.example.leagueoflegendsapi.ui.screens.MainScreen
import com.example.leagueoflegendsapi.ui.screens.MatchInfoScreen
import com.example.leagueoflegendsapi.ui.viewModel.RiotViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val riotViewModel: RiotViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreens.MainScreen
    ) {

        composable<AppScreens.MainScreen> {
            MainScreen(
                onMatchClick = { matchId->
                    navController.navigate(AppScreens.MatchInfoScreen(matchId))
                },
                riotViewModel = riotViewModel,
            )
        }

        composable<AppScreens.MatchInfoScreen> { backStackEntry ->

            val args = backStackEntry.toRoute<AppScreens.MatchInfoScreen>()

            MatchInfoScreen(
                matchId = args.matchId,
                riotViewModel = riotViewModel,
                navController = navController
            )
        }
    }
}
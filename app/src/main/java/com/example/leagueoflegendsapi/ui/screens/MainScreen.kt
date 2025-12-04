package com.example.leagueoflegendsapi.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.leagueoflegendsapi.model.RankedQueue
import com.example.leagueoflegendsapi.ui.components.MatchesCardComponent
import com.example.leagueoflegendsapi.ui.components.RankedFlexCardComponent
import com.example.leagueoflegendsapi.ui.components.RankedSoloCardComponent
import com.example.leagueoflegendsapi.ui.components.SearchBarComponent
import com.example.leagueoflegendsapi.ui.components.SummonerInfoCardComponent
import com.example.leagueoflegendsapi.ui.viewModel.RiotViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    riotViewModel: RiotViewModel = viewModel(),
    onMatchClick: (String) -> Unit,
) {

    val summonerInfo by riotViewModel.summonerInfo
    val rankedInfoList by riotViewModel.summonerRanked
    val matchesList by  riotViewModel.matchDetailsList
    val myPuuid by riotViewModel.puuid

    val soloDuoData = rankedInfoList.find {
        it.queueType == RankedQueue.SOLO_DUO.apiName
    }

    val flexData = rankedInfoList.find {
        it.queueType == RankedQueue.FLEX.apiName
    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            SearchBarComponent(
                riotViewModel = riotViewModel,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            item {
            if (summonerInfo.isNotEmpty() && !summonerInfo.startsWith("Error")) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                ) {
                    SummonerInfoCardComponent(
                        riotViewModel = riotViewModel
                    )
                }
                if (soloDuoData != null || flexData != null) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (flexData != null) {
                            Box(
                                modifier = Modifier
                                    .padding(bottom = 15.dp)
                            ) {
                                RankedFlexCardComponent(
                                    rankedData = flexData,
                                    riotViewModel,
                                )
                            }
                        }
                        if (soloDuoData != null) {
                            Box(
                                modifier = Modifier
                                    .padding(start = 15.dp, bottom = 15.dp)
                            ) {
                                RankedSoloCardComponent(
                                    rankedData = soloDuoData,
                                    riotViewModel = riotViewModel
                                )
                            }
                        }
                    }
                }
            } else if (summonerInfo.startsWith("Error")) {
                Text(
                    text = summonerInfo
                )
            } else {
                Text(
                    text = "Busca un jugador para empezar."
                )
            }
        }
            if (summonerInfo.isNotEmpty() && !summonerInfo.startsWith("Error")){
                items(matchesList) { match ->
                    MatchesCardComponent(
                        modifier = Modifier
                            .padding(top = 5.dp),
                        match = match,
                        myPuuid = myPuuid,
                        navController = navController,
                    )
                }
            }
        }
    }
}
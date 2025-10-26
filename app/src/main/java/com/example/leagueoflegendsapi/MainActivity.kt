package com.example.leagueoflegendsapi


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.leagueoflegendsapi.ui.theme.LeagueOfLegendsAPITheme
import com.example.leagueoflegendsapi.ui.theme.viewModel.RiotViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LeagueOfLegendsAPITheme {
                SummonerInfoScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummonerInfoScreen(riotViewModel: RiotViewModel = viewModel()) {
    var gameName by remember { mutableStateOf("") }
    var tagLine by remember { mutableStateOf("") }

    // Observar el estado del ViewModel
    val summonerInfo by riotViewModel.summonerInfo

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("League of Legends") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = gameName,
                onValueChange = { gameName = it },
                label = { Text("Summoner name") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = tagLine,
                onValueChange = { tagLine = it },
                label = { Text("Tag Line") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = {
                riotViewModel.getSummonerData(gameName, tagLine)
            }) {
                Text("Search")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(summonerInfo)
        }
    }
}


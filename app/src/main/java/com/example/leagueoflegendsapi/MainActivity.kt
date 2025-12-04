package com.example.leagueoflegendsapi


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.leagueoflegendsapi.data.navigation.AppNavigation
import com.example.leagueoflegendsapi.ui.screens.MainScreen

import com.example.leagueoflegendsapi.ui.theme.LeagueOfLegendsAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LeagueOfLegendsAPITheme {
                AppNavigation()
            }
        }
    }
}



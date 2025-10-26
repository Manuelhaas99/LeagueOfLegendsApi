package com.example.leagueoflegendsapi.ui.theme.viewModel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leagueoflegendsapi.ui.theme.data.RetrofitClient
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf


class RiotViewModel : ViewModel() {

    private val apiKey = "RGAPI-b499ac21-6df0-47e9-a320-eeabcbc1dbd3" // replace with your real key

    private val _summonerInfo = mutableStateOf("Enter a summoner name")
    val summonerInfo: State<String> = _summonerInfo

    fun getSummonerData(summonerName: String, tagLine: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getSummonerByName(summonerName, tagLine, apiKey)
                val summoner = RetrofitClient.api.getSummonerByPuuid(response.puuid, apiKey)
                val rankedInfo = RetrofitClient.api.getRankedInfo(summoner.id, apiKey)

                val infoText = StringBuilder()
                infoText.append("Name: ${response.gameName}#${response.tagLine}\n")
                infoText.append("Level: ${summoner.summonerLevel}\n\n")

                rankedInfo.forEach {
                    infoText.append("${it.queueType}: ${it.tier} ${it.rank} (${it.leaguePoints} LP) - Wins: ${it.wins}, Losses: ${it.losses}\n")
                }

                _summonerInfo.value = infoText.toString()

            } catch (e: Exception) {
                _summonerInfo.value = "Error: ${e.message}"
            }
        }
    }
}

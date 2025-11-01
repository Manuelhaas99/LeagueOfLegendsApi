package com.example.leagueoflegendsapi.ui.viewModel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leagueoflegendsapi.data.client.RetrofitClient
import kotlinx.coroutines.launch


class RiotViewModel : ViewModel() {

    private val apiKey = "RGAPI-83a5a3dc-a73f-4146-b074-7db118027d7b" // replace with your real key

    private val _summonerInfo = mutableStateOf("Enter a summoner name")
    val summonerInfo: State<String> = _summonerInfo

    private val _summonerAccount = mutableStateOf("")
    val summonerAccount: State<String> = _summonerAccount

    private val _puuid = mutableStateOf("")
    val puuid: State<String> = _puuid


    fun getSummonerData(summonerName: String, tagLine: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getSummonerByName(summonerName, tagLine, apiKey)
                _puuid.value = response.puuid

                val infoText = buildString {
                    append("Name: ${response.gameName}\n")
                    append("Tag line: #${response.tagLine}\n")
                    append("Puuid: ${response.puuid}\n")
                    append("Summoner Level: ${response.summonerLevel}\n")
                    append("Profile icon: ${response.profileIconId}\n")
                }

                _summonerInfo.value = infoText
                getAccountMatches(response.puuid)

            } catch (e: Exception) {
                _summonerInfo.value = "Error: ${e.message}"
            }
        }
    }

    fun getAccountMatches(puuid: String) {

        viewModelScope.launch {
            try {
                val responseAccount = RetrofitClient.api.getSummonerByPuuid(puuid, apiKey)

                val infoAccount = buildString {
                    append("Summoner level: ${responseAccount.summonerLevel}\n")
                    append("Profile Icon ID: ${responseAccount.profileIconId}\n")
                }
                _summonerAccount.value = infoAccount

            } catch (e: Exception) {
                _summonerAccount.value = "Error account: ${e.message}"
            }
        }
    }
}


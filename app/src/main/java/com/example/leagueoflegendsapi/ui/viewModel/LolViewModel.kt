package com.example.leagueoflegendsapi.ui.viewModel


import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leagueoflegendsapi.data.client.RetrofitClient
import com.example.leagueoflegendsapi.data.repository.LolRepository
import com.example.leagueoflegendsapi.data.repository.LolRepositoryI
import com.example.leagueoflegendsapi.model.ChampionFullResponse
import com.example.leagueoflegendsapi.model.MatchDetail
import com.example.leagueoflegendsapi.model.RankedData
import com.example.leagueoflegendsapi.model.Summoner
import com.example.leagueoflegendsapi.model.SummonerData
import com.example.leagueoflegendsapi.utils.getSplashArtUtils
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlin.math.log


class RiotViewModel(
    private val client: LolRepositoryI = LolRepository(
        RetrofitClient.americaApi,
        RetrofitClient.laApi,
    )
) : ViewModel() {

    private val _summonerInfo = mutableStateOf("")
    val summonerInfo: State<String> = _summonerInfo

    private val _summonerAccount = mutableStateOf("")
    val summonerAccount: State<String> = _summonerAccount

    private val _summonerRanked = mutableStateOf<List<RankedData>>(emptyList())
    val summonerRanked: State<List<RankedData>> = _summonerRanked

    private val _summonerMatches = mutableStateOf<List<String>>(emptyList())
    val summonerMatches: State<List<String>> = _summonerMatches

    private val _matchDetailsList = mutableStateOf<List<MatchDetail>>(emptyList())
    val matchDetailsList: State<List<MatchDetail>> = _matchDetailsList

    private val _summonerData = mutableStateOf<SummonerData?>(null)
    val summonerData: State<SummonerData?> = _summonerData

    private val _backgroundValue = mutableStateOf<String>("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-splashes/266/266000.jpg")
    val backgroundValue: State<String> = _backgroundValue

    private val _summoner = mutableStateOf<Summoner?>(null)
    val summoner: State<Summoner?> = _summoner

    private val _puuid = mutableStateOf("")
    val puuid: State<String> = _puuid

    private val _matchId = mutableStateOf("")
    val matchId: State<String> = _matchId

    init {
        randomizeBackground()
    }


    fun searchByRiotId(riotIdQuery: String) {
        if (!riotIdQuery.contains("#")) {
            _summonerInfo.value = "Error: Formato incorrecto. Usa UserName#Tag"
            _summonerAccount.value = ""
            return
        }
        val parts = riotIdQuery.split("#", limit = 2)
        val gameName = parts[0].trim()
        val tagLine = parts[1].trim()

        if (gameName.isBlank() || tagLine.isBlank()) {
            _summonerInfo.value = "Error: El nombre y el tag no pueden estar vacios."
            _summonerAccount.value = ""
        }

        getSummonerData(gameName, tagLine)
    }

    fun getSummonerData(summonerName: String, tagLine: String) {
        viewModelScope.launch {
            try {
                val response = client.getAccount(summonerName, tagLine)
                _summoner.value = response

                _puuid.value = response.puuid

                val infoText = buildString {
                    append("${response.gameName}\n")
                    append("#${response.tagLine}\n")
                }

                _summonerInfo.value = infoText
                getSummonerData(response.puuid)
                getRankedDataByPuuid(response.puuid)
                getMatchByPuuid(response.puuid)

            } catch (e: Exception) {
                _summonerInfo.value = "Error: ${e.message}"
            }
        }
    }

    fun getSummonerData(puuid: String) {
        viewModelScope.launch {
            try {
                val responseAccount = client.getSummonerData(puuid)

                _summonerData.value = responseAccount

                val infoAccount = buildString {
                    append("${responseAccount.summonerLevel}\n")
                }
                _summonerAccount.value = infoAccount

            } catch (e: Exception) {
                _summonerAccount.value = "Error account: ${e.message}"
                _summonerData.value = null
            }
        }
    }

    fun getRankedDataByPuuid(puuid: String) {
        viewModelScope.launch {
            try {
                val responseRankedList = client.getRankedByPuuid(puuid)
                Log.d("ViewModelDebug", "Tamaño de la lista recibida: ${responseRankedList.size}")
                _summonerRanked.value = responseRankedList

            } catch (e: Exception) {
                Log.e("ViewModelError", "Error al parsear RankedData: ${e.message}", e)
                _summonerRanked.value = emptyList()
            }
        }
    }

    fun getMatchByPuuid(puuid: String){
        viewModelScope.launch {
            try {
               val matchIdList = client.getMatchByPuuid(puuid)

                val deferredResults = matchIdList.take(20).map { matchId ->
                    async {
                        try {
                            client.getMatchByMatchId(matchId)
                        }catch (e: Exception){
                            Log.e("ViewModelError", "Fallo al obtener detalles para $matchId", e)
                            null
                        }
                    }
                }
                _matchDetailsList.value = deferredResults.awaitAll().filterNotNull()
            }catch (e: Exception){
                _matchDetailsList.value = emptyList()
            }
        }
    }

    fun randomizeBackground() {
        viewModelScope.launch {
            try {
                val url = "https://ddragon.leagueoflegends.com/cdn/14.23.1/data/en_US/championFull.json"

                val response = client.getChampions(url)

                val newSkinUrl = getRandomSkin(response)

                _backgroundValue.value = newSkinUrl

                Log.d("Randomizer", "URL Final: $newSkinUrl")

            } catch (e: Exception) {
                Log.e("Randomizer", "Error: ${e.message}")
            }
        }
    }

    private fun getRandomSkin(response: ChampionFullResponse): String {
        val allChampions = response.data.values.toList()

        val randomChamp = allChampions.random()
        val randomSkin = randomChamp.skins.random()

        val champName = randomChamp.id

        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${champName}_${randomSkin.num}.jpg"
    }

}


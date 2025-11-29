package com.example.leagueoflegendsapi.utils


// Esta función es perfecta para Data Dragon
fun getSplashArtUtils(championName: String, skinNum: Int = 0): String {
    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${championName}_${skinNum}.jpg"
}
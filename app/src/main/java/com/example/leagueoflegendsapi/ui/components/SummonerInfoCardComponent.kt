package com.example.leagueoflegendsapi.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.leagueoflegendsapi.ui.viewModel.RiotViewModel

@Composable
fun SummonerInfoCardComponent(
    riotViewModel: RiotViewModel,
) {

    val info by riotViewModel.summonerInfo
    val summonerData by riotViewModel.summonerData
    val accountInfo by riotViewModel.summonerAccount
    val localData = summonerData
    val summoner by riotViewModel.summoner
    val localDataSummoner = summoner
    val skinUrl by riotViewModel.backgroundValue
    android.util.Log.d("SkinDebug", "URL recibida en la UI: '$skinUrl'")

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = skinUrl,
                contentDescription = "Fondo",
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .matchParentSize()
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.6f))
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (localData != null && localDataSummoner != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Row(
                            modifier = Modifier
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(
                                        top = 10.dp,
                                        start = 10.dp,
                                        bottom = 10.dp,
                                        end = 10.dp
                                    ),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                val profileIconUrl =
                                    "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/profile-icons/${localData.profileIconId}.jpg"

                                AsyncImage(
                                    model = profileIconUrl,
                                    contentDescription = "Icono de perfil",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                )
                                Text(
                                    text = localData.summonerLevel.toString(),
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .offset(y = 6.dp)
                                        .background(
                                            color = MaterialTheme.colorScheme.onBackground,
                                            shape = RoundedCornerShape(14.dp)
                                        )
                                        .padding(horizontal = 5.dp, vertical = 2.dp)
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .align(alignment = Alignment.CenterVertically)
                            ) {
                                Text(
                                    text = localDataSummoner.gameName,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.White
                                )
                                Text(
                                    text = "#${localDataSummoner.tagLine ?: "N/A"}",
                                    fontStyle = FontStyle.Italic,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                            }
                        }
                    }
                } else {
                    Text(
                        text = info,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(top = 4.dp, start = 8.dp)
                    )
                    Text(
                        text = accountInfo,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}
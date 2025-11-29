package com.example.leagueoflegendsapi.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.leagueoflegendsapi.model.MatchDetail
import com.example.leagueoflegendsapi.utils.GameDurationSeconds
import com.example.leagueoflegendsapi.utils.QueueType
import com.example.leagueoflegendsapi.utils.getRuneStyleIconUrl
import com.example.leagueoflegendsapi.utils.getSpellNameById
import com.example.leagueoflegendsapi.utils.getTimeAgo
import com.example.leagueoflegendsapi.utils.runesIconsUtil


@Composable
fun MatchesCardComponent(
    modifier: Modifier = Modifier,
    match: MatchDetail,
    myPuuid: String,
) {
    val myParticipant = match.info.participants.find { it.puuid == myPuuid }
    val gameDurationText = GameDurationSeconds(match.info)
    Log.d("TimeDebug", "ID: ${match.metadata.matchId} - Timestamp: ${match.info.gameStartTimestamp}")
    val timeAgoText = getTimeAgo(match.info.gameStartTimestamp)

    if (myParticipant != null) {
        val itemsId = listOf(
            myParticipant.item0,
            myParticipant.item1,
            myParticipant.item2,
            myParticipant.item3,
            myParticipant.item4,
            myParticipant.item5,
            myParticipant.item6
        )

        val spell1Name = getSpellNameById(myParticipant.summoner1Id)
        val spell2Name = getSpellNameById(myParticipant.summoner2Id)
        val queueGameMode = QueueType(match.info.queueId)

        val perks = myParticipant.perks

        val keyStoneId = perks.styles[0].selections[0].perk
        val keystoneUrl = runesIconsUtil(keyStoneId)

        val secondaryStyleId = perks.styles[1].style
        val secondaryUrl = getRuneStyleIconUrl(secondaryStyleId)

        val backgroundColor = if (myParticipant.win == true)
            Color(0xFF226AE8)
        else
            Color(0xFFDE1D38)

        val backgroundColorDarker = if (myParticipant.win == true)
            Color(0xFF193BA3)
        else
            Color(0xFFB3051F)

        val spell1Url =
            "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/data/spells/icons2d/${spell1Name}.png"
        val spell2Url =
            "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/data/spells/icons2d/${spell2Name}.png"

        val champIconsUrl =
            "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-icons/${myParticipant.championId}.png"

        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor,
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = modifier
                .fillMaxSize()
                .size(width = 150.dp, 100.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(50.dp)
                        .background(
                            color = backgroundColorDarker,
                            shape = RoundedCornerShape(topStart = 6.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (myParticipant.win == true) "Win" else "Loss",
                        fontStyle = FontStyle.Italic,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        text = gameDurationText,
                        fontStyle = FontStyle.Italic,
                        color = Color.White
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier

                    ) {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            AsyncImage(
                                model = champIconsUrl,
                                contentDescription = "Images for the 1st summoner spells",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                            )
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(start = 5.dp)
                                ) {
                                    Box(
                                        modifier = Modifier

                                    ) {
                                        AsyncImage(
                                            model = spell1Url,
                                            contentDescription = "Images for the 1st summoner spells",
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier
                                                .size(24.dp)
                                        )
                                    }
                                    Box(
                                        modifier = Modifier

                                    ) {
                                        AsyncImage(
                                            model = spell2Url,
                                            contentDescription = "Images for the 2nd summoner spells",
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier
                                                .size(24.dp)
                                        )
                                    }
                                }
                                Column(
                                    modifier = Modifier
                                        .padding(start = 5.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                    ) {
                                        AsyncImage(
                                            model = keystoneUrl,
                                            contentDescription = "First Rune",
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier
                                                .size(24.dp)
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                    ) {
                                        AsyncImage(
                                            model = secondaryUrl,
                                            contentDescription = "Second Rune",
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier
                                                .size(24.dp)
                                        )
                                    }
                                }
                                Column(
                                    modifier = Modifier
                                        .padding(start = 5.dp),
                                ) {
                                    Text(
                                        text = "${myParticipant.kills} / ${myParticipant.deaths} / ${myParticipant.assists}",
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(top = 5.dp),
                        ) {
                            itemsId.forEach { itemId ->
                                ItemImage(itemId = itemId)
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 5.dp, end = 15.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = queueGameMode,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        text = timeAgoText,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ItemImage(itemId: Int?) {
    if (itemId == 0) {
        Box(
            modifier = Modifier
                .size(27.dp)
                .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(10.dp))
        ) {

        }
    } else {
        val itemsUrl = "https://ddragon.leagueoflegends.com/cdn/14.23.1/img/item/${itemId}.png"

        AsyncImage(
            model = itemsUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(27.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray)
        )
    }
}


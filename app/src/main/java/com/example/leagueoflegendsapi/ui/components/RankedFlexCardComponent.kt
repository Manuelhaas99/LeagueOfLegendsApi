package com.example.leagueoflegendsapi.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.leagueoflegendsapi.model.RankedData
import com.example.leagueoflegendsapi.ui.viewModel.RiotViewModel
import java.util.Locale

@Composable
fun RankedFlexCardComponent(
    rankedData: RankedData,
    riotViewModel: RiotViewModel,
) {
    val tierName = (rankedData.tier?.ifBlank { "unranked" }
        ?: "unranked").lowercase(java.util.Locale.getDefault())
    val emblemUrl =
        "https://raw.communitydragon.org/latest/plugins/rcp-fe-lol-static-assets/global/default/images/ranked-emblem/emblem-${tierName}.png"

    val totalGames = rankedData.wins + rankedData.losses
    val winrate = if(totalGames > 0) {
        (rankedData.wins.toFloat() / totalGames) * 100
    } else {
        0f
    }

    ElevatedCard(
        modifier = Modifier
            .size(height = 120.dp, width = 280.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                AsyncImage(
                    model = emblemUrl,
                    contentDescription = "Emblema del tier",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(130.dp)
                        .scale(3f)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Ranked flex",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                )
                Text(
                    text = "${rankedData.tier} ${rankedData.rank}",
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                )
                Text(
                    text = "${rankedData.leaguePoints}LP",
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                )
                Text(
                    text = "Winrate: ${rankedData.wins}W / ${rankedData.losses}L \n%${(winrate.toInt())}",
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                )
            }
        }
    }
}

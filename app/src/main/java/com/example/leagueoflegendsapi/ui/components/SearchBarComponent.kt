package com.example.leagueoflegendsapi.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leagueoflegendsapi.ui.viewModel.RiotViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    riotViewModel: RiotViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    var query by rememberSaveable { mutableStateOf("") }
    var isActive by rememberSaveable { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SearchBar(
            modifier = Modifier
                .padding(start = 7.dp, end = 7.dp)
//                .padding(horizontal = 16.dp),
                    ,
            windowInsets = WindowInsets.statusBars,
            query = query,
            onQueryChange = { query = it },
            onSearch = {
                riotViewModel.searchByRiotId(query)
                isActive = false
            },
            active = isActive,
            onActiveChange = { isActive = it },
            placeholder = {
                Text(
                    buildAnnotatedString {
                        append("Player name + ")
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF1976D2),
                                background = Color(0x332197D2),
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("#Tag")
                        }
                    }
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
            }
        }
    }
}



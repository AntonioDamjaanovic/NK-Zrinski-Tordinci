package com.example.nkzrinskitordinci.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nkzrinskitordinci.data.ClubViewModel
import com.example.nkzrinskitordinci.R
import com.example.nkzrinskitordinci.Routes

@Composable
fun UpdatePlayerStatsScreen(
    clubViewModel: ClubViewModel,
    navigation: NavController,
    playerId: Int
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(navigation, "Dodaj podatke")
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
        ) {
            StatsInputForm(clubViewModel, navigation, playerId)
        }
        BlackBottomBar()
    }
}

@Composable
fun StatsInputForm(
    clubViewModel: ClubViewModel,
    navigation: NavController,
    playerId: Int
) {
    val player = clubViewModel.playersData.getOrNull(playerId)

    var minutesPlayed by remember { mutableStateOf("") }
    var goalsScored by remember { mutableStateOf("") }
    var assistsProvided by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)
    ) {
        CustomTextField(caption = "Minute", value = minutesPlayed, onValueChange = { minutesPlayed = it })
        CustomTextField(caption = "Pogotci", value = goalsScored, onValueChange = { goalsScored = it })
        CustomTextField(caption = "Asistencije", value = assistsProvided, onValueChange = { assistsProvided = it })

        IconButton(iconResource = R.drawable.ic_plus, "Spremi podatke") {
            player?.let {
                it.gamesPlayed++
                it.minutesPlayed += minutesPlayed.toIntOrNull() ?:0
                it.goalsScored += goalsScored.toIntOrNull() ?:0
                it.assistsProvided += assistsProvided.toIntOrNull() ?:0

                clubViewModel.updatePlayerStats(it)
                navigation.popBackStack(Routes.SCREEN_PLAYER_DETAILS, false)
            }
        }
    }
}
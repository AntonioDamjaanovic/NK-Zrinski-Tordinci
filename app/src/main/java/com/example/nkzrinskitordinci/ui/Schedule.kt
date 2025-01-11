package com.example.nkzrinskitordinci.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nkzrinskitordinci.data.ClubViewModel
import com.example.nkzrinskitordinci.data.Game

@Composable
fun GamesList(
    clubViewModel: ClubViewModel
) {
    val games = clubViewModel.gamesData

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        items(games) { game ->
            GameItem(game)
        }
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun GameItem(
    game: Game
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RectangleShape
            )
            .padding(10.dp)
    ) {
        Column {
            val dateTime = game.time.split(" ")
            Text(
                text = dateTime[0],
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = dateTime[1],
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.width(30.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = game.homeTeam,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
            Text(
                text = game.awayTeam,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
        Column {
            val result = game.result.split(":")
            Text(
                text = result[0],
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = result[1],
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
    }
}

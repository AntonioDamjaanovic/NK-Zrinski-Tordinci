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
import com.example.nkzrinskitordinci.data.Game

@Composable
fun GamesList() {
    val games = mutableListOf(
        Game("NK Zrinski (T)", "NK Nosteria", "25.08.2024. 17:00", "2:3"),
        Game("NK Mladost (P)", "NK Zrinski (T)", "01.09.2024. 17:00", "1:0"),
        Game("NK Zrinski (T)", "NK Vidor Matijević", "08.09.2024. 16:30", "3:4"),
        Game("NK Polet (DNS)", "NK Zrinski (T)", "15.09.2024. 16:30", "0:5"),
        Game("NK Zrinski (T)", "NK Borac (R)", "22.09.2024. 16:00", "3:1"),
        Game("NK Croatia (NJ)", "NK Zrinski (T)", "29.09.2024. 15:30", "3:2"),
        Game("NK Zrinski (T)", "NK Hajduk Mirko", "06.10.2024. 15:30", "4:1"),
        Game("NK Mladost (A)", "NK Zrinski (T)", "13.10.2024. 15:30", "0:1"),
        Game("NK Zrinski (T)", "NK Marinci", "20.10.2024. 14:00", "4:1"),
        Game("NK Sremac (M)", "NK Zrinski (T)", "27.10.2024. 14:00", "1:2"),
        Game("NK Zrinski (T)", "NK Šokadija (SM)", "03.11.2024. 14:00", "3:1"),
        Game("NK Zrinski (T)", "NK Sloga (NM)", "10.11.2024. 14:00", "4:0"),
        Game("NK Slavonac (P)", "NK Zrinski (T)", "17.11.2024. 14:00", "1:3")
    )
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
fun GameItem(game: Game) {
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
        Spacer(modifier = Modifier.width(50.dp))
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

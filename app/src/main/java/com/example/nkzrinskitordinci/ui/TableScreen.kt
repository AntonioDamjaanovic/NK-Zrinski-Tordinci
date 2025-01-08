package com.example.nkzrinskitordinci.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.nkzrinskitordinci.data.Team
import com.example.nkzrinskitordinci.data.ClubViewModel

fun sortTeamsByPoints(teams: MutableList<Team>): List<Team> {
    teams.sortWith(compareByDescending<Team> { it.points }
        .thenByDescending { it.goalDifference })
    return teams
}

@Composable
fun TableList(
    clubViewModel: ClubViewModel
) {
    val teams = clubViewModel.teamsData
    val sortedTeams = sortTeamsByPoints(teams)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        item {
            TableHeader()
        }
        itemsIndexed(sortedTeams) { index, team ->
            TeamRow(position = index + 1, team = team)
        }
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun TableHeader() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "#",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.weight(0.5f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Momčad",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.weight(3.2f),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "O",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.weight(0.6f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "P",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.weight(0.6f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "N",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.weight(0.6f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "I",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.weight(0.6f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "+/-",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "GR",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.weight(0.6f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "B",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.weight(0.6f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TeamRow(
    position: Int,
    team: Team
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
        Text(
            text = "$position",
            modifier = Modifier.weight(0.5f),
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = team.name,
            modifier = Modifier.weight(3.2f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            maxLines = 1
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "${team.played}",
            modifier = Modifier.weight(0.6f),
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${team.won}",
            modifier = Modifier.weight(0.6f),
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${team.drawn}",
            modifier = Modifier.weight(0.6f),
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${team.lost}",
            modifier = Modifier.weight(0.6f),
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${team.goalsFor}:${team.goalsAgainst}",
            modifier = Modifier.weight(1f),
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${team.goalDifference}",
            modifier = Modifier.weight(0.6f),
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${team.points}",
            modifier = Modifier.weight(0.6f),
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}


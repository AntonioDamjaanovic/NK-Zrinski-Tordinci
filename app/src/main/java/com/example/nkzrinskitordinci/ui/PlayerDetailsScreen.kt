package com.example.nkzrinskitordinci.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nkzrinskitordinci.R
import com.example.nkzrinskitordinci.Routes
import com.example.nkzrinskitordinci.data.Player
import com.example.nkzrinskitordinci.data.PlayerViewModel
import com.example.nkzrinskitordinci.ui.theme.DarkGray
import com.example.nkzrinskitordinci.ui.theme.LightGray
import com.example.nkzrinskitordinci.ui.theme.White

@Composable
fun PlayerDetailsScreen(
    viewModel: PlayerViewModel,
    navigation: NavController,
    playerId: Int
) {
    val player = viewModel.playersData[playerId]
    Column {
        TopImageAndBar(navigation, player)
        PlayerStats(player)
    }

}

@Composable
fun CircularButton(
    @DrawableRes iconResource: Int,
    color: Color = White,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(defaultElevation = 12.dp),
    onClick: () -> Unit = { }
) {
    Button(
        contentPadding = PaddingValues(),
        elevation = elevation,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = color),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(25.dp)
            .height(25.dp)
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = null
        )
    }
}

@Composable
fun TopImageAndBar(
    navigation: NavController,
    player: Player
) {
    Column {
        Box(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth()
                .background(Color.Red),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp)
                ) {
                    Spacer(modifier = Modifier.width(20.dp))
                    CircularButton(R.drawable.ic_arrow_back) {
                        navigation.popBackStack(Routes.SCREEN_ALL_PLAYERS, false)
                    }
                    Text(
                        text = "Profil igrača",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                }
            }
        }
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(LightGray),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_player_icon),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                ) {
                    Text(
                        text = player.name,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.ic_zrinski_grb),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                        Text(
                            text = "NK Zrinski (T)",
                            fontSize = 18.sp,
                            color = DarkGray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlayerStats(player: Player) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
        ) {
            Text(
                text = "Pozicija:",
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = player.position,
                fontSize = 20.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
        ) {
            Text(
                text = "Nastupi:",
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${player.gamesPlayed}",
                fontSize = 20.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
        ) {
            Text(
                text = "Minute:",
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${player.minutesPlayed}",
                fontSize = 20.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
        ) {
            Text(
                text = "Pogotci:",
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${player.goalsScored}",
                fontSize = 20.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
        ) {
            Text(
                text = "Asistencije:",
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${player.assistsProvided}",
                fontSize = 20.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

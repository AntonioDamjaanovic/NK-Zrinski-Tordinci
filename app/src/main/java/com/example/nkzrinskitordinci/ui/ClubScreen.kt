package com.example.nkzrinskitordinci.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nkzrinskitordinci.R
import com.example.nkzrinskitordinci.Routes
import com.example.nkzrinskitordinci.data.ClubViewModel
import com.example.nkzrinskitordinci.ui.theme.DarkGray
import com.example.nkzrinskitordinci.ui.theme.LightGray
import com.example.nkzrinskitordinci.ui.theme.Pink
import com.example.nkzrinskitordinci.ui.theme.White

@Composable
fun ClubScreen(
    clubViewModel: ClubViewModel,
    navigation: NavController
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        ScreenTitle(title = "NK Zrinski Tordinci")
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            ClubCategories(clubViewModel, navigation)
        }
        BlackBottomBar()
    }
}

@Composable
fun ScreenTitle(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(Color.Red),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Text(
            text = title,
            style = TextStyle(
                color = White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 15.dp)
        )
    }
}

@Composable
fun TabButton(
    text: String,
    isActive: Boolean,
    onClick: () -> Unit
) {
    Button(
        shape = RectangleShape,
        elevation = null,
        colors = if (isActive) ButtonDefaults.buttonColors(
            contentColor = White, containerColor = Pink
        ) else ButtonDefaults.buttonColors(
            contentColor = DarkGray, containerColor = LightGray
        ),
        modifier = Modifier.fillMaxHeight(),
        onClick = { onClick() }
    ) {
        Text(text)
    }
}

@Composable
fun ClubCategories(
    clubViewModel: ClubViewModel,
    navigation: NavController
) {
    var currentActiveButton by remember { mutableIntStateOf(0) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .background(Color.Transparent)
                .fillMaxWidth()
                .height(44.dp)
        ) {
            TabButton(text = "MOMČAD", isActive = currentActiveButton == 0) {
                currentActiveButton = 0
            }
            TabButton(text = "RASPORED", isActive = currentActiveButton == 1) {
                currentActiveButton = 1
            }
            TabButton(text = "TABLICA", isActive = currentActiveButton == 2) {
                currentActiveButton = 2
            }
        }

        when (currentActiveButton) {
            0 -> PlayerList(clubViewModel, navigation)
            1 -> GamesList(clubViewModel)
            2 -> TableList(clubViewModel)
        }
    }
}

@Composable
fun PlayerList(
    clubViewModel: ClubViewModel,
    navigation: NavController
) {
    LazyColumn(
        modifier = Modifier.padding(20.dp)
    ) {
        item {
            Text(text = "Igrači", style = MaterialTheme.typography.titleMedium)
        }
        items(clubViewModel.playersData.size) {
            PlayerRow(
                playerName = clubViewModel.playersData[it].name
            ) {
                navigation.navigate(
                    Routes.getPlayerDetailsPath(it)
                )
            }
        }
        item {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp)
            ) {
                IconButton(
                    iconResource = R.drawable.ic_plus,
                    text = "Dodaj igrača",
                ) {
                    navigation.navigate(Routes.SCREEN_ADD_PLAYER)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun PlayerRow(
    playerName: String,
    onClick: () -> Unit
) {
    Row(
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
            .clickable {
                onClick()
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_player_icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = playerName,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_croatian_flag),
            contentDescription = "Zastava",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun IconButton(
    @DrawableRes iconResource: Int,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Pink, contentColor = White),
        modifier = Modifier.width(200.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
            )
            Spacer(Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = text,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun BlackBottomBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Black)
    )
}
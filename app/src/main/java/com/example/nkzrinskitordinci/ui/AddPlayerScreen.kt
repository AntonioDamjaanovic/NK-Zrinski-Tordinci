package com.example.nkzrinskitordinci.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nkzrinskitordinci.R
import com.example.nkzrinskitordinci.data.ClubViewModel
import com.example.nkzrinskitordinci.data.Player
import com.example.nkzrinskitordinci.ui.theme.Blue
import com.example.nkzrinskitordinci.ui.theme.DarkGray
import com.example.nkzrinskitordinci.ui.theme.Gray
import com.example.nkzrinskitordinci.ui.theme.LightGray
import com.example.nkzrinskitordinci.ui.theme.Pink
import com.example.nkzrinskitordinci.ui.theme.Transparent
import com.example.nkzrinskitordinci.ui.theme.White

@Composable
fun AddPlayerScreen(
    clubViewModel: ClubViewModel,
    navigation: NavController
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(navigation, "Dodaj igrača")
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
        ) {
            PlayerInputForm(clubViewModel, navigation)
        }
        BlackBottomBar()
    }
}

@Composable
fun PlayerInputForm(
    clubViewModel: ClubViewModel,
    navigation: NavController
) {
    var name by remember { mutableStateOf("") }
    var position by remember { mutableStateOf("") }
    var gamesPlayed by remember { mutableStateOf("") }
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
        CustomTextField(caption = "Ime", value = name, onValueChange = { name = it })
        CustomTextField(caption = "Pozicija", value = position, onValueChange = { position = it })
        CustomTextField(caption = "Odigrane utakmice", value = gamesPlayed, onValueChange = { gamesPlayed = it })
        CustomTextField(caption = "Odigrane minute", value = minutesPlayed, onValueChange = { minutesPlayed = it })
        CustomTextField(caption = "Golovi", value = goalsScored, onValueChange = { goalsScored = it })
        CustomTextField(caption = "Asistencije", value = assistsProvided, onValueChange = { assistsProvided = it })

        val player = Player(
            "", name, position,
            gamesPlayed.toIntOrNull() ?: 0,
            minutesPlayed.toIntOrNull() ?: 0,
            goalsScored.toIntOrNull() ?: 0,
            assistsProvided.toIntOrNull() ?: 0
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
        ) {
            IconButton (
                iconResource = R.drawable.ic_plus,
                text = "Dodaj igrača"
            ) {
                if (name.isNotEmpty() && position.isNotEmpty()) {
                    clubViewModel.addPlayer(player)
                    navigation.popBackStack()
                } else {
                    Log.e("PlayerInputForm", "Error with input fields!")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    caption: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Text(
        text = caption,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        textAlign = TextAlign.Start,
        fontSize = 14.sp,
        color = Pink
    )
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
            .height(50.dp),
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(color = Pink, fontSize = 16.sp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = LightGray,
            cursorColor = Color.Black,
            disabledLabelColor = DarkGray,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
    )
}

@Composable
fun TopBar(
    navigation: NavController,
    text: String
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
                        navigation.popBackStack()
                    }
                    Text(
                        text = text,
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
    }
}

package com.example.nkzrinskitordinci.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ClubViewModel: ViewModel() {
    private val db = Firebase.firestore

    val teamsData = mutableStateListOf<Team>()
    val gamesData = mutableStateListOf<Game>()
    val playersData = mutableStateListOf<Player>()


    init {
        fetchDatabaseData()
    }

    private fun fetchDatabaseData() {
        db.collection("teams")
            .get()
            .addOnSuccessListener { result ->
                for (data in result.documents) {
                    val team = data.toObject(Team::class.java)

                    if (team != null) {
                        team.id = data.id
                        teamsData.add(team)
                    }
                }
            }
        db.collection("games")
            .get()
            .addOnSuccessListener { result ->
                for (data in result.documents) {
                    val game = data.toObject(Game::class.java)

                    if (game != null) {
                        game.id = data.id
                        gamesData.add(game)
                    }
                }
            }
        db.collection("players")
            .get()
            .addOnSuccessListener { result ->
                for (data in result.documents) {
                    val player = data.toObject(Player::class.java)

                    if (player != null) {
                        player.id = data.id
                        playersData.add(player)
                    }
                }
            }
    }
}
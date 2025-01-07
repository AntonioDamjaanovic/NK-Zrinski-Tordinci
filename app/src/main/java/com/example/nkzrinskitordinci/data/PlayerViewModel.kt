package com.example.nkzrinskitordinci.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class PlayerViewModel: ViewModel() {
    private val db = Firebase.firestore

    val playersData = mutableStateListOf<Player>()

    init {
        fetchDatabaseData()
    }

    private fun fetchDatabaseData() {
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
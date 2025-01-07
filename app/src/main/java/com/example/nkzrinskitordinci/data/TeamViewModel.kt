package com.example.nkzrinskitordinci.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class TeamViewModel: ViewModel() {
    private val db = Firebase.firestore

    val teamsData = mutableStateListOf<Team>()

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
    }
}
package com.example.nkzrinskitordinci.data

data class Player(
    var id: String = "",
    val name: String = "",
    val position: String = "",
    var gamesPlayed: Int = 0,
    var minutesPlayed: Int = 0,
    var goalsScored: Int = 0,
    var assistsProvided: Int = 0
)
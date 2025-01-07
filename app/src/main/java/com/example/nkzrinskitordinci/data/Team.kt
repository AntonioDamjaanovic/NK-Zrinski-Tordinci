package com.example.nkzrinskitordinci.data

data class Team(
    var id: String = "",
    val name: String = "",
    var played: Int = 0,
    var won: Int = 0,
    var drawn: Int = 0,
    var lost: Int = 0,
    var goalsFor: Int = 0,
    var goalsAgainst: Int = 0,
    var goalDifference: Int = 0,
    var points: Int = 0
)

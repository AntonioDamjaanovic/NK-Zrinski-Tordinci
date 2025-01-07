package com.example.nkzrinskitordinci.data

data class Team(
    val name: String,
    var played: Int,
    var won: Int,
    var drawn: Int,
    var lost: Int,
    var goalsFor: Int,
    var goalsAgainst: Int,
    var goalDifference: Int,
    var points: Int
)

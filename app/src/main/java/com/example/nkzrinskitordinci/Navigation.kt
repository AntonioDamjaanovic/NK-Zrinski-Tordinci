package com.example.nkzrinskitordinci

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nkzrinskitordinci.data.ClubViewModel
import com.example.nkzrinskitordinci.ui.AddPlayerScreen
import com.example.nkzrinskitordinci.ui.ClubScreen
import com.example.nkzrinskitordinci.ui.PlayerDetailsScreen

object Routes {
    const val SCREEN_ALL_PLAYERS = "playerList"
    const val SCREEN_PLAYER_DETAILS = "playerDetails/{playerId}"
    const val SCREEN_ADDING_PLAYER = "addPlayer"

    fun getPlayerDetailsPath(playerId: Int?) : String {
        if (playerId != null && playerId != -1) {
            return "playerDetails/$playerId"
        }
        return "playerDetails/0"
    }
}

@Composable
fun NavigationController(
    clubViewModel: ClubViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.SCREEN_ALL_PLAYERS
    ) {
        composable(Routes.SCREEN_ALL_PLAYERS) {
            ClubScreen(
                clubViewModel = clubViewModel,
                navigation = navController
            )
        }
        composable(
            Routes.SCREEN_PLAYER_DETAILS,
            arguments = listOf(
                navArgument("playerId") {
                    type = NavType.IntType
                }
            )
        ) {
            backStackEntry ->
                backStackEntry.arguments?.getInt("playerId")?.let {
                    PlayerDetailsScreen(
                        clubViewModel = clubViewModel,
                        navigation = navController,
                        playerId = it
                    )
                }
        }
        composable(Routes.SCREEN_ADDING_PLAYER) {
            AddPlayerScreen(
                clubViewModel = clubViewModel,
                navigation = navController
            )
        }
    }
}
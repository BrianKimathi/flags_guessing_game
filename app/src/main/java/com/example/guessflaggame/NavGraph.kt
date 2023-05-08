package com.example.guessflaggame

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.guessflaggame.ui.FlagGameViewModel
import com.example.guessflaggame.ui.GuessFlagScreen
import com.example.guessflaggame.ui.StartGameScreen

@Composable
fun NavGraphSetUp(
    navHostController: NavHostController,
    gameViewModel: FlagGameViewModel = viewModel()
) {
    val gameUIState by gameViewModel.uiState.collectAsState()

    NavHost(
        navController = navHostController,
        startDestination = Screen.Start.route
    ){
        composable(
            route = Screen.Start.route
        ){
            StartGameScreen (
                onStartButtonClick = {
                    navHostController.navigate(route = Screen.Guess.route)
                }
            )
        }

        composable(
            route = Screen.Guess.route
        ){
            GuessFlagScreen(
                userCountryNameGuess = gameViewModel.userGuess,
                onUserGuessChanged = {
                    gameViewModel.updateUserGuess(it)
                },
                currentGuessCount = gameUIState.currentGuessCount,
                currentScore = gameUIState.score,
                currentFlagImg = gameUIState.currentFlag.flagImg,
                isGuessWrong = gameUIState.isGuessNameWrong,
                onNextButtonClicked = {
                    gameViewModel.checkUserGuess()
                },
                isGameOver = gameUIState.isGameOver,
                onSkipButtonClicked = {
                    gameViewModel.skip()
                },
                onPlayAgain = {
                    gameViewModel.resetGame()
                }
            )
        }

    }
}
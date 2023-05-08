package com.example.guessflaggame.models

data class GameUIState(
    val currentFlag: Flag = Flag(0, ""),
    val isGuessNameWrong: Boolean = false,
    val score: Int = 0,
    val currentGuessCount: Int = 0,
    val isGameOver: Boolean = false
)

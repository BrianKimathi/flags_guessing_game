package com.example.guessflaggame

sealed class Screen(val route: String){
    object Start: Screen("start_game_screen")
    object Guess: Screen("guess_flag_screen")
}

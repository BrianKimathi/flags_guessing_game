package com.example.guessflaggame.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import com.example.guessflaggame.data.MAX_NO_OF_GUESSES
import com.example.guessflaggame.data.SCORE_INCREASE
import com.example.guessflaggame.data.flags
import com.example.guessflaggame.models.Flag
import com.example.guessflaggame.models.GameUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FlagGameViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(GameUIState())
    val uiState: StateFlow<GameUIState> = _uiState.asStateFlow()

    private lateinit var currentFlag: Flag
    private val usedFlags: MutableSet<Flag> = mutableSetOf()
    private lateinit var currentFlagName: String

    var userGuess by mutableStateOf("")
        private set


    init {
        resetGame()
    }

    fun resetGame() {
        usedFlags.clear()
        _uiState.value = GameUIState(currentFlag = pickRandomFlag())
    }

    private fun pickRandomFlag(): Flag{
        currentFlag = flags.random()
        return if (usedFlags.contains(currentFlag)){
            pickRandomFlag()
        }else{
            usedFlags.add(currentFlag)
            currentFlagName = currentFlag.country
            currentFlag
        }
    }

    fun updateUserGuess(guessName: String){
        userGuess = guessName
    }

    private fun updateGameState(updatedScore: Int){
        if (usedFlags.size == MAX_NO_OF_GUESSES){
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessNameWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
        }else{
            // Normal round in the game
            _uiState.update { currentState ->
                currentState.copy(
                    currentFlag = pickRandomFlag(),
                    isGuessNameWrong = false,
                    currentGuessCount = currentState.currentGuessCount.inc(),
                    score = updatedScore
                )
            }
        }
    }

    fun checkUserGuess(){
        if (userGuess.equals(currentFlagName, ignoreCase = true)){
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
            updateUserGuess("")
        }else{
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessNameWrong = true
                )
            }
        }
    }

    fun skip() {
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }
}
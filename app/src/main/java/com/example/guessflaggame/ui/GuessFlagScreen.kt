@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.guessflaggame.ui

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guessflaggame.R
import com.example.guessflaggame.ui.theme.blue

@Composable
fun GuessFlagScreen(
    userCountryNameGuess: String,
    onUserGuessChanged: (String) -> Unit,
    currentGuessCount: Int,
    currentScore: Int,
    currentFlagImg: Int,
    isGuessWrong: Boolean,
    onNextButtonClicked: () -> Unit,
    onSkipButtonClicked: () -> Unit,
    isGameOver: Boolean,
    onPlayAgain: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.guess_count, currentGuessCount))
            Text(text = stringResource(id = R.string.score, currentScore))
        }

        Spacer(Modifier.height(10.dp))

        Image(
            painter = painterResource(id = currentFlagImg),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(Modifier.height(10.dp))

        FlagNameInputField(
            userCountryNameGuess,
            onUserGuessChanged,
            isGuessWrong
        )

        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = onSkipButtonClicked,
                border = BorderStroke(1.dp, blue)
            ) {
                Text(
                    text = stringResource(id = R.string.skip),
                    color = blue,
                    fontSize = 14.sp
                )
            }

            OutlinedButton(
                onClick = onNextButtonClicked,
                border = BorderStroke(1.dp, blue)
            ) {
                Text(
                    text = stringResource(id = R.string.next),
                    color = blue,
                    fontSize = 14.sp
                )
            }
        }

        if (isGameOver){
            FinalScoreDialog(
                currentScore,
                onPlayAgain = onPlayAgain
            )
        }

    }
}

@Composable
fun FlagNameInputField(
    userCountryNameGuess: String,
    onUserGuessChanged: (String) -> Unit,
    isGuessWrong: Boolean
) {
    OutlinedTextField(
        value = userCountryNameGuess,
        onValueChange = onUserGuessChanged,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = blue,
            unfocusedBorderColor = blue
        ),
        isError = isGuessWrong,
        singleLine = true,
        label = {
            if (isGuessWrong){
                Text(text = stringResource(id = R.string.wrong_guess), color = blue)
            }else{
                Text(text = stringResource(id = R.string.label_text), color = blue)
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(stringResource(R.string.congratulations)) },
        text = { Text(stringResource(R.string.you_scored, score)) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onPlayAgain()
                }
            ) {
                Text(text = stringResource(R.string.play_again))
            }
        }
    )
}

@Composable
@Preview(showSystemUi = true, device = Devices.PIXEL_2)
fun GuessFlagPreview() {
//    GuessFlagScreen()
}
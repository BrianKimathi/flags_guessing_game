package com.example.guessflaggame.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guessflaggame.R
import com.example.guessflaggame.ui.theme.cyan

@Composable
fun StartGameScreen(
    onStartButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(
            text = stringResource(id = R.string.game_title),
            color = Color.Blue,
            fontSize = 24.sp,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.kenya),
                contentDescription = null
            )

            Image(
                painter = painterResource(id = R.drawable.question_mark),
                contentDescription = null,
                modifier = Modifier.height(150.dp)
            )
        }

        OutlinedButton(
            onClick = onStartButtonClick,
            border = BorderStroke(1.dp, cyan)
        ) {
            Text(
                text = stringResource(id = R.string.start),
                color = Color.Blue,
            )
        }

    }
}

@Composable
@Preview(showSystemUi = true, device = Devices.PIXEL_2)
fun StartScreenPreview() {
    StartGameScreen(onStartButtonClick = {  })
}
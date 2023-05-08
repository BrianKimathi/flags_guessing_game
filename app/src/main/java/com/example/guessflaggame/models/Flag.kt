package com.example.guessflaggame.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Flag(
    @DrawableRes val flagImg: Int,
    val country: String
)

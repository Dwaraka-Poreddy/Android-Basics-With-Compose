package com.example.happybirthday.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class WellnessGoal(
    @StringRes val summaryResId: Int,
    @StringRes val descriptionResId: Int,
    @DrawableRes val imageResId: Int,
)

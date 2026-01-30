package com.example.happybirthday.ui.screens.businesscardscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.happybirthday.R

@Composable
fun BusinessCardScreen(modifier: Modifier = Modifier) {
    Column (modifier = modifier
        .fillMaxSize()
        .background(colorResource(R.color.business_card_bg)).
        padding(horizontal = 16.dp)
    ) {
        ProfileSection(
            modifier = Modifier.weight(1f)
        )
        ContactSection(
            modifier = Modifier.padding(bottom = 24.dp)
        )
    }
}
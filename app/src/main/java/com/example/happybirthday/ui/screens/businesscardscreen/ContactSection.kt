package com.example.happybirthday.ui.screens.businesscardscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ContactSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        ContactRow(
            icon = Icons.Default.Call,
            text = "+11 (123) 444 555 666",
            contentDescription = "Phone Icon"
        )

        ContactRow(
            icon = Icons.Default.Share,
            text = "@AndroidDev",
            contentDescription = "Share Handle"
        )

        ContactRow(
            icon = Icons.Default.Email,
            text = "@jen.android.com",
            contentDescription = "Email"
        )

    }
}
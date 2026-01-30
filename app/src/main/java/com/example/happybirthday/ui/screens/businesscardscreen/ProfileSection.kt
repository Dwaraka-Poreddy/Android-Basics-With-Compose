package com.example.happybirthday.ui.screens.businesscardscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.R

@Composable
fun ProfileSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val image = painterResource(R.drawable.android_logo)
        Image(
            painter = image,
            contentDescription = "Android Logo",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Jennifer Doe",
            fontSize = 36.sp,
            fontWeight = FontWeight.Light,
            color = Color.Black
        )
        Text(
            text = "Android Developer Extraordinaire",
            color = Color(0xFF3b644c),
            fontWeight = FontWeight.Bold,
        )
    }
}

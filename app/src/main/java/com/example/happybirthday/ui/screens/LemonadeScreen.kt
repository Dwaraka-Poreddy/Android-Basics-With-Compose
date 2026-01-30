package com.example.happybirthday.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.R

data class LemonadeStep(
    val imageResourceId: Int,
    val contentDescriptionResourceId: Int,
    val stepDescriptionResourceId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeScreen(modifier: Modifier = Modifier) {
    var result by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }
    val steps = arrayOf(
        LemonadeStep(
            R.drawable.lemon_tree,
            R.string.lemonade_lemon_tree,
            R.string.lemonade_select_lemon
        ),
        LemonadeStep(
            R.drawable.lemon_squeeze,
            R.string.lemonade_lemon,
            R.string.lemonade_squeeze_lemon
        ),
        LemonadeStep(R.drawable.lemon_drink, R.string.lemonade_glass, R.string.lemonade_drink),
        LemonadeStep(
            R.drawable.lemon_restart,
            R.string.lemonade_empty_glass,
            R.string.lemonade_restart
        )
    )
    val currentStep = steps[result - 1]
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold,
                        color = Color(0XFF000000)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFEEE29B)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(currentStep.imageResourceId),
                contentDescription = stringResource(currentStep.contentDescriptionResourceId),
                modifier = modifier.clickable(enabled = true) {
                    when (result) {
                        1 -> {
                            squeezeCount = (2..4).random()
                            result = 2
                        }

                        2 -> {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                result = 3
                            }
                        }

                        3 -> {
                            result = 4
                        }

                        else -> {
                            result = 1
                        }
                    }
                })
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(currentStep.stepDescriptionResourceId), fontSize = 18.sp)
        }
    }
}

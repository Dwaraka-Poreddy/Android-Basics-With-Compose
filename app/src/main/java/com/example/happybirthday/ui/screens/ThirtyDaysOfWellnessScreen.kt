package com.example.happybirthday.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.R
import com.example.happybirthday.data.WellnessGoalDataSource
import com.example.happybirthday.model.WellnessGoal
import com.example.happybirthday.ui.theme.SuperheroesTheme

@Composable
fun ThirtyDaysOfWellnessScreen(modifier: Modifier = Modifier) {
    val layoutDirection = LocalLayoutDirection.current
    SuperheroesTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(
                    start = WindowInsets.safeDrawing.asPaddingValues()
                        .calculateStartPadding(layoutDirection),
                    end = WindowInsets.safeDrawing.asPaddingValues()
                        .calculateEndPadding(layoutDirection)
                )
        ) {
            ThirtyDaysOfWellnessApp(
                wellnessGoals = WellnessGoalDataSource().loadWellnessGoals()
            )
        }
    }
}

@Composable
fun ThirtyDaysOfWellnessApp(
    wellnessGoals: List<WellnessGoal>,
) {
    Scaffold(
        topBar = {
            WellnessAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(wellnessGoals) {
                WellnessGoalItem(
                    wellnessGoal = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(R.string.wellness_app_name),
                style = MaterialTheme.typography.displayLarge
            )
        }
    )
}

@Composable
fun WellnessGoalItem(
    wellnessGoal: WellnessGoal,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.secondaryContainer
    )
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = color)
                .clickable(enabled = true) {
                    expanded = !expanded
                }
        ) {
            Text(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small)),
                text = "Day 1",
                style = MaterialTheme.typography.bodySmall.copy(
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.padding_small)),
                text = stringResource(wellnessGoal.summaryResId),
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = 18.sp,
                    fontSize = 18.sp,
                ),
            )
            Spacer(modifier = modifier.height(2.dp))
            WellnessGoalImage(
                imageRes = wellnessGoal.imageResId
            )
            if (expanded)
                Text(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_small),
                            bottom = dimensionResource(R.dimen.padding_small),
                        ),
                    text = stringResource(wellnessGoal.descriptionResId),
                    style = MaterialTheme.typography.bodyLarge
                )
        }
    }
}

@Composable
fun WellnessGoalImage(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.padding_small),
                end = dimensionResource(R.dimen.padding_small),
                bottom = dimensionResource(R.dimen.padding_small),
            ),
        painter = painterResource(imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}
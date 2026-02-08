package com.example.happybirthday.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.R
import com.example.happybirthday.data.superheroes
import com.example.happybirthday.model.Superhero
import com.example.happybirthday.ui.theme.SuperheroesTheme

@Composable
fun SuperheroesScreen(modifier: Modifier = Modifier) {
    SuperheroesTheme {
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            SuperheroesApp()
        }
    }
}

@Composable
fun SuperheroesApp() {
    Scaffold (
        topBar = {
            SuperheroesAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
        items(superheroes) {
            SuperheroItem(
                superhero = it,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    } }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroesAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(R.string.superhero_app_name),
                style = MaterialTheme.typography.displayLarge
            )
        }
    )
}

@Composable
fun SuperheroItem(
    superhero: Superhero,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            SuperheroInfo(
                nameRes = superhero.nameRes,
                descriptionRes = superhero.descriptionRes,
                modifier = Modifier.weight(1f)
            )
            SuperheroImage(
                imageRes = superhero.imageRes,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun SuperheroImage(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .requiredSize(dimensionResource(R.dimen.super_hero_image_size))
            .clip(MaterialTheme.shapes.small),
        painter = painterResource(imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun SuperheroInfo(
    @StringRes nameRes: Int,
    @StringRes descriptionRes: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(nameRes),
            style = MaterialTheme.typography.displayMedium.copy(
                lineHeight = 18.sp
            ),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(descriptionRes),
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 18.sp
            )
        )
    }
}
package com.example.happybirthday.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.happybirthday.R

@Composable
fun ComposeQuadrantScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = Modifier.weight(1f)) {
            ComposeQuadrant(
                title = stringResource(R.string.compose_quadrant_text_title),
                description = stringResource(R.string.compose_quadrant_text_description),
                backgroundColor = colorResource(R.color.text_quadrant),
                modifier = Modifier.weight(1f)
            )
            ComposeQuadrant(
                title = stringResource(R.string.compose_quadrant_image_title),
                description = stringResource(R.string.compose_quadrant_image_description),
                backgroundColor = colorResource(R.color.image_quadrant),
                modifier = Modifier.weight(1f)
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            ComposeQuadrant(
                title = stringResource(R.string.compose_quadrant_row_title),
                description = stringResource(R.string.compose_quadrant_row_description),
                backgroundColor = colorResource(R.color.row_quadrant),
                modifier = Modifier.weight(1f)
            )
            ComposeQuadrant(
                title = stringResource(R.string.compose_quadrant_column_title),
                description = stringResource(R.string.compose_quadrant_column_description),
                backgroundColor = colorResource(R.color.column_quadrant),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
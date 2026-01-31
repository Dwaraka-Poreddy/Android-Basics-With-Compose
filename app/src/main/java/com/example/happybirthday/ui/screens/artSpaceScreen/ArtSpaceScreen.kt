package com.example.happybirthday.ui.screens.artSpaceScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.happybirthday.R

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val imagesArr = arrayOf(
        "https://images.unsplash.com/photo-1577084381380-3b9ea4153664",
        "https://images.unsplash.com/photo-1605721911519-3dfeb3be25e7",
        "https://plus.unsplash.com/premium_photo-1675813863340-b7e84c4a1fb0",
        "https://images.unsplash.com/photo-1578301978162-7aae4d755744",
        "https://images.unsplash.com/photo-1579541513287-3f17a5d8d62c",
        "https://images.unsplash.com/photo-1579783900882-c0d3dad7b119"
    )
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = modifier
                    .padding(16.dp)
                    .shadow(elevation = 4.dp)
                    .aspectRatio(3f / 4f),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imagesArr[currentIndex],
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .shadow(2.dp)
                .background(Color(0xFFeceaf4), MaterialTheme.shapes.extraSmall)
                .padding(16.dp)
        ) {
            Text(
                text = "Sailing Under the Bridge",
                style = MaterialTheme.typography.headlineSmall.copy(
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Author Name",
                    style = MaterialTheme.typography.titleSmall.copy(
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "(2017)",
                    style = MaterialTheme.typography.labelSmall.copy(
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            }
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                contentPadding = PaddingValues(
                    horizontal = 40.dp,
                    vertical = 2.dp,
                ),
                onClick = {
                if (currentIndex == 0) currentIndex = imagesArr.size - 1 else currentIndex -= 1
            }) {
                Text(text = "Previous")
            }
            Button(
                contentPadding = PaddingValues(
                    horizontal = 60.dp,
                    vertical = 2.dp,
                ),
                onClick = {
                if (currentIndex == imagesArr.size - 1) currentIndex = 0 else currentIndex += 1
            }) {
                Text(text = "Next")
            }
        }
    }
}
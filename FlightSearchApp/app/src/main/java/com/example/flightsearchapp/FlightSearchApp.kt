package com.example.flightsearchapp

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearchapp.ui.FlightSearchViewModel
import com.example.flightsearchapp.ui.FlightSearchViewModel.AppViewModelProvider
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.data.FavoriteEntity

@Composable
fun FlightSearchApp(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val viewModel: FlightSearchViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        TextField(
            value = uiState.searchQuery,
            onValueChange = {
                viewModel.onSearchQueryChanged(it)
            },
            label = { Text("Search airport") },
            modifier = Modifier.fillMaxWidth()
        )
        when {
            uiState.searchQuery.isEmpty() -> {
                LazyColumn {
                    items(uiState.favorites) { favorite ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Column {

                                    Text(
                                        text = "FROM",
                                        style = MaterialTheme.typography.labelSmall
                                    )

                                    Text(
                                        text = "${favorite.departureCode} - ${favorite.departureName}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "TO",
                                        style = MaterialTheme.typography.labelSmall
                                    )

                                    Text(
                                        text = "${favorite.destinationCode} - ${favorite.destinationName}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Remove from favorites",
                                    modifier = Modifier.clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) {
                                        viewModel.onRemoveFavorite(
                                            FavoriteEntity(
                                                id = favorite.id,
                                                departureCode = favorite.departureCode,
                                                destinationCode = favorite.destinationCode
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }

            uiState.selectedAirport != null -> {
                LazyColumn {
                    items(uiState.destinations) { airport ->
                        val isFavorite = uiState.favorites.any {
                            it.departureCode == uiState.selectedAirport?.iataCode &&
                                    it.destinationCode == airport.iataCode
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(text = airport.iataCode)
                                Text(text = airport.name)
                            }

                            Icon(
                                imageVector = if (isFavorite) {
                                    Icons.Filled.Favorite
                                } else {
                                    Icons.Outlined.FavoriteBorder
                                },
                                contentDescription = "Add to favorites",
                                modifier = Modifier.clickable(
                                    indication = LocalIndication.current,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    val departure =
                                        uiState.selectedAirport?.iataCode ?: return@clickable

                                    if (isFavorite) {
                                        // remove
                                        val favorite = uiState.favorites.find {
                                            it.departureCode == departure &&
                                                    it.destinationCode == airport.iataCode
                                        } ?: return@clickable

                                        viewModel.onRemoveFavorite(
                                            FavoriteEntity(
                                                id = favorite.id,
                                                departureCode = favorite.departureCode,
                                                destinationCode = favorite.destinationCode
                                            )
                                        )
                                    } else {
                                        // add
                                        viewModel.onAddFavorite(
                                            FavoriteEntity(
                                                departureCode = departure,
                                                destinationCode = airport.iataCode,
                                                id = 0
                                            )
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }

            else -> {
                LazyColumn {
                    items(uiState.suggestions) { airport ->
                        Text(
                            text = "${airport.iataCode} - ${airport.name}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    viewModel.onAirportSelected(airport)
                                }
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

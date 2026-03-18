import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.flightsearchapp.data.AirportEntity
import com.example.flightsearchapp.data.FavoriteEntity
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.data.FavoriteWithAirports
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text

@Composable
fun DestinationList(
    selectedAirport: AirportEntity,
    destinations: List<AirportEntity>,
    favorites: List<FavoriteWithAirports>,
    onAdd: (FavoriteEntity) -> Unit,
    onRemove: (FavoriteEntity) -> Unit,
    snackbarHostState: SnackbarHostState,
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.secondary.copy(alpha = 0.02f)
            )
            .padding(8.dp)
    ) {

        Text(
            text = "✈️ From ${selectedAirport.iataCode} • Destinations",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(destinations) { airport ->

                val isFavorite = favorites.any {
                    it.departureCode == selectedAirport.iataCode &&
                            it.destinationCode == airport.iataCode
                }

                DestinationItem(
                    airport = airport,
                    isFavorite = isFavorite,
                    onClick = {
                        if (isFavorite) {
                            val fav = favorites.find {
                                it.departureCode == selectedAirport.iataCode &&
                                        it.destinationCode == airport.iataCode
                            } ?: return@DestinationItem

                            onRemove(
                                FavoriteEntity(
                                    id = fav.id,
                                    departureCode = fav.departureCode,
                                    destinationCode = fav.destinationCode
                                )
                            )
                        } else {
                            onAdd(
                                FavoriteEntity(
                                    id = 0,
                                    departureCode = selectedAirport.iataCode,
                                    destinationCode = airport.iataCode
                                )
                            )
                        }
                    }
                )
            }
        }
    }
}

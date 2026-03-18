import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearchapp.ui.FlightSearchViewModel
import com.example.flightsearchapp.ui.FlightSearchViewModel.AppViewModelProvider
import com.example.flightsearchapp.ui.SearchBar

@Composable
fun FlightSearchApp(
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val viewModel: FlightSearchViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
    val uiState by viewModel.uiState.collectAsState()

    BackHandler(
        enabled = uiState.searchQuery.isNotEmpty() || uiState.selectedAirport != null
    ) {
        viewModel.onSearchQueryChanged("")
    }

    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(16.dp)
    ) {

        SearchBar(
            query = uiState.searchQuery,
            onQueryChange = viewModel::onSearchQueryChanged
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            uiState.searchQuery.isEmpty() -> {
                FavoritesList(
                    favorites = uiState.favorites,
                    onRemove = viewModel::onRemoveFavorite,
                    snackbarHostState = snackbarHostState
                )
            }

            uiState.selectedAirport != null -> {
                DestinationList(
                    selectedAirport = uiState.selectedAirport!!,
                    destinations = uiState.destinations,
                    favorites = uiState.favorites,
                    onAdd = viewModel::onAddFavorite,
                    onRemove = viewModel::onRemoveFavorite
                )
            }

            else -> {
                SuggestionsList(
                    suggestions = uiState.suggestions,
                    onClick = viewModel::onAirportSelected
                )
            }
        }
    }
}

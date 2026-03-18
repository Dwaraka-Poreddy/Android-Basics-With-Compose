import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.flightsearchapp.data.FavoriteEntity
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.data.FavoriteWithAirports

@Composable
fun FavoritesList(
    favorites: List<FavoriteWithAirports>,
    onRemove: (FavoriteEntity) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Column(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.primary.copy(alpha = 0.03f)
            )
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "⭐ Saved Routes",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .fillMaxWidth()
        )

        if (favorites.isEmpty()) {
            Text(
                text = "No favorites yet. Start exploring routes ✈️",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(favorites) { favorite ->
                    FavoriteCard(
                        favorite, onRemove, snackbarHostState
                    )
                }
            }
        }
    }
}

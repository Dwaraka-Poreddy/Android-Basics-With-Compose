import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.flightsearchapp.data.AirportEntity
import androidx.compose.foundation.lazy.items

@Composable
fun SuggestionsList(
    suggestions: List<AirportEntity>,
    onClick: (AirportEntity) -> Unit
) {
    LazyColumn {
        items(suggestions) { airport ->
            SuggestionItem(airport, onClick)
        }
    }
}
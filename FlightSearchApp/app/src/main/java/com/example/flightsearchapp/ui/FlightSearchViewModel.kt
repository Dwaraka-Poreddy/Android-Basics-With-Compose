package com.example.flightsearchapp.ui

import android.provider.SearchRecentSuggestions
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Query
import com.example.flightsearchapp.FlightSearchApplication
import com.example.flightsearchapp.data.AirportEntity
import com.example.flightsearchapp.data.AirportRepository
import com.example.flightsearchapp.data.FavoriteEntity
import com.example.flightsearchapp.data.FavoriteRepository
import com.example.flightsearchapp.data.FavoriteWithAirports
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FlightSearchUiState(
    val searchQuery: String = "",
    val suggestions: List<AirportEntity> = emptyList(),
    val selectedAirport: AirportEntity? = null,
    val destinations: List<AirportEntity> = emptyList(),
    val favorites: List<FavoriteWithAirports> = emptyList()
)

class FlightSearchViewModel(
    private val airportRepository: AirportRepository,
    private val favoriteRepository: FavoriteRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(FlightSearchUiState())
    val uiState: StateFlow<FlightSearchUiState> = _uiState

    init {
        observeFavorites()
    }

    object AppViewModelProvider {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                FlightSearchViewModel(
                    airportRepository = application.container.airportRepository,
                    favoriteRepository = application.container.favoriteRepository,
                )
            }
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            favoriteRepository.getFavoritesWithAirports().collect {  favorites ->
                _uiState.update {
                    it.copy(
                        favorites = favorites
                    )
                }

            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update {
            it.copy(
                searchQuery = query,
                selectedAirport = null
            )
        }
        if(query.isBlank()){
            _uiState.update {
                it.copy(
                    suggestions = emptyList()
                )
            }
        } else {
            viewModelScope.launch {
                airportRepository.getAutoCompleteSuggestions(query)
                    .collect { suggestions ->
                        _uiState.update {
                            it.copy(suggestions = suggestions)
                        }
                    }
            }
        }
    }

    fun onAirportSelected(airport: AirportEntity) {
        _uiState.update {
            it.copy(
                selectedAirport = airport,
                suggestions = emptyList()
            )
        }
        viewModelScope.launch {
            airportRepository.getAllDestinations(airport.iataCode)
                .collect { destinations ->
                    _uiState.update {
                        it.copy(destinations = destinations)
                    }
                }
        }
    }

    fun onAddFavorite(favorite: FavoriteEntity) {
        viewModelScope.launch {
            favoriteRepository.insertFavorite(favorite)
        }
    }

    fun onRemoveFavorite(favorite: FavoriteEntity) {
        viewModelScope.launch {
            favoriteRepository.deleteFavorite(favorite)
        }
    }

    fun onClearSearch() {
        _uiState.update {
            it.copy(
                searchQuery = "",
                selectedAirport = null,
                suggestions = emptyList(),
                destinations = emptyList()
            )
        }
    }
}
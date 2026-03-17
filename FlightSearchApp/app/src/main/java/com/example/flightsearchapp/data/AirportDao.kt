package com.example.flightsearchapp.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query("SELECT * FROM airport WHERE iata_code LIKE :query OR name LIKE :query ORDER BY passengers DESC")
    fun getAutoCompleteSuggestions(query: String): Flow<List<AirportEntity>>

    @Query("SELECT * FROM airport WHERE iata_code != :iataCode ORDER BY passengers DESC")
    fun getAllDestinations(iataCode: String): Flow<List<AirportEntity>>
}

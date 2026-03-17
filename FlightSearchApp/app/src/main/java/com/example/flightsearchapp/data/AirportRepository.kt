package com.example.flightsearchapp.data

import kotlinx.coroutines.flow.Flow

interface AirportRepository {
    fun getAutoCompleteSuggestions(query: String): Flow<List<AirportEntity>>
    fun getAllDestinations(iataCode: String): Flow<List<AirportEntity>>
}

class OfflineAirportRepository(private val airportDao: AirportDao) : AirportRepository {
    override fun getAutoCompleteSuggestions(query: String): Flow<List<AirportEntity>> {
        return airportDao.getAutoCompleteSuggestions("%$query%")
    }

    override fun getAllDestinations(iataCode: String): Flow<List<AirportEntity>> {
        return airportDao.getAllDestinations(iataCode)
    }

}
package com.example.flightsearchapp.data

data class FavoriteWithAirports(
    val id: Int,
    val departureCode: String,
    val destinationCode: String,
    val departureName: String,
    val destinationName: String
)
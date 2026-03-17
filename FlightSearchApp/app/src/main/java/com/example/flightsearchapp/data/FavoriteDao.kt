package com.example.flightsearchapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: FavoriteEntity)

    @Delete
    suspend fun delete(favorite: FavoriteEntity)

    @Query("SELECT * from favorite")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("""
    SELECT 
        f.id,
        f.departure_code AS departureCode,
        f.destination_code AS destinationCode,
        dep.name AS departureName,
        dest.name AS destinationName
    FROM favorite f
    JOIN airport dep ON f.departure_code = dep.iata_code
    JOIN airport dest ON f.destination_code = dest.iata_code
""")
    fun getFavoritesWithAirports(): Flow<List<FavoriteWithAirports>>
}
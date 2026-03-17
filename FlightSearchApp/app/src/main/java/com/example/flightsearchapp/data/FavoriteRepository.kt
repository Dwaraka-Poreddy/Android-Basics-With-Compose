package com.example.flightsearchapp.data

import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun insertFavorite(favorite: FavoriteEntity)
    suspend fun deleteFavorite(favorite: FavoriteEntity)
    fun getAllFavorites(): Flow<List<FavoriteEntity>>
    fun getFavoritesWithAirports(): Flow<List<FavoriteWithAirports>>
}

class OfflineFavoriteRepository( private val favoriteDao: FavoriteDao) : FavoriteRepository {
    override suspend fun insertFavorite(favorite: FavoriteEntity) {
        return favoriteDao.insert(favorite)
    }

    override suspend fun deleteFavorite(favorite: FavoriteEntity) {
        return favoriteDao.delete(favorite)
    }

    override fun getAllFavorites(): Flow<List<FavoriteEntity>> {
        return favoriteDao.getAllFavorites()
    }

    override fun getFavoritesWithAirports(): Flow<List<FavoriteWithAirports>> {
        return favoriteDao.getFavoritesWithAirports()
    }

}
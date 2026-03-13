package com.example.bookshelf.network

import com.example.bookshelf.data.ApiResponse
import com.example.bookshelf.data.Book
import retrofit2.http.GET

interface BookShelfApiService {
    @GET("volumes?q=secret")
    suspend fun getBooks(): ApiResponse
}

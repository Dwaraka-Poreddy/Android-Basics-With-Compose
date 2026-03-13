package com.example.bookshelf.data

import com.example.bookshelf.network.BookShelfApiService

interface BookShelfRepository {
    suspend fun getBooks(): List<Book>
}

class NetworkBookShelfRepository(
    private val bookShelfApiService: BookShelfApiService
) : BookShelfRepository {
    override suspend fun getBooks(): List<Book> = bookShelfApiService.getBooks().items
}
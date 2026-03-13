package com.example.bookshelf.data

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val items: List<Book>
)

@Serializable
data class Book(
    val id: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo,
)

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>? = null,
    val publisher: String? = null,
    val description: String? = null,
    val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    val thumbnail: String? = null,
    val smallThumbnail: String? = null
)

@Serializable
data class SaleInfo(
    val listPrice: Price? = null,
    val retailPrice: Price? = null,
    val buyLink: String? = null
)

@Serializable
data class Price(
    val amount: Float,
    val currencyCode: String
)

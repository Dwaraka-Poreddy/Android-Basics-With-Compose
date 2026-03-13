package com.example.bookshelf.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import coil.network.HttpException
import com.example.bookshelf.data.Book
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.BookShelfRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BookShelfUiState {
    data class Success(val books: List<Book> ) : BookShelfUiState
    object Error : BookShelfUiState
    object Loading : BookShelfUiState
}

class BookShelfViewModel(
    private val bookShelfRepository: BookShelfRepository
) : ViewModel() {
    var bookShelfUiState: BookShelfUiState by mutableStateOf(BookShelfUiState.Loading)
        private set

    init {
        getBooks()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookShelfApplication)
                val bookShelfRepository = application.container.bookShelfRepository
                BookShelfViewModel(bookShelfRepository = bookShelfRepository)
            }
        }
    }

    fun getBooks() {
        viewModelScope.launch {
            bookShelfUiState = BookShelfUiState.Loading
            bookShelfUiState = try {
                val books = bookShelfRepository.getBooks()
                Log.d("Books", books.toString())
                BookShelfUiState.Success(books)
            } catch (e: IOException) {
                BookShelfUiState.Error
            } catch (e: HttpException) {
                BookShelfUiState.Error
            }
        }
    }
}
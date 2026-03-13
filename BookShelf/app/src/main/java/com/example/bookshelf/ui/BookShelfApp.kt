package com.example.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.bookshelf.ui.screens.BookShelfViewModel
import com.example.bookshelf.ui.screens.HomeScreen
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelf(modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {BookShelfTopAppBar(scrollBehavior = scrollBehavior)}
    ) {
        Surface (modifier = Modifier.fillMaxSize().padding(it)
        ) {
            val bookShelfViewModel: BookShelfViewModel = viewModel(factory = BookShelfViewModel.Factory)
            HomeScreen(
                bookShelfUiState = bookShelfViewModel.bookShelfUiState,
                retryAction = bookShelfViewModel::getBooks,
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "Book Shelf",
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}
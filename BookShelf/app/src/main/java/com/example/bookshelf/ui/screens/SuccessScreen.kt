package com.example.bookshelf.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bookshelf.R
import com.example.bookshelf.data.Book

@Composable
fun SuccessScreen(
    books: List<Book>, modifier: Modifier = Modifier
) {
    var selectedBook by remember { mutableStateOf<Book?>(null) }
    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(books, key = { it.id }) { book ->
                BookGridItem(
                    book = book, onClick = { selectedBook = book })
            }
        }

        selectedBook?.let { book ->
            BookDetailsSheet(
                book = book, onDismiss = { selectedBook = null })
        }
    }
}

@Composable
fun BookGridItem(
    book: Book, onClick: () -> Unit
) {
    val imageUrl = book.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://")
    Card(
        modifier = Modifier
            .width(140.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = book.volumeInfo.title,
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Text(
                text = book.volumeInfo.title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsSheet(
    book: Book, onDismiss: () -> Unit
) {
    val imageUrl = book.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://")
    val context = LocalContext.current
    ModalBottomSheet(
        onDismissRequest = onDismiss, dragHandle = { BottomSheetDefaults.DragHandle() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 8.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = book.volumeInfo.title,
                modifier = Modifier
                    .height(220.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.FillHeight,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = book.volumeInfo.title, style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            BookInfoRow(
                label = "Author", value = book.volumeInfo.authors?.joinToString() ?: "Unknown"
            )
            BookInfoRow(
                label = "Publisher", value = book.volumeInfo.publisher ?: "Unknown"
            )
            book.saleInfo.retailPrice?.let {
                BookInfoRow(
                    label = "Price", value = "${it.amount} ${it.currencyCode}", highlight = true
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "Description", style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = book.volumeInfo.description ?: "No description available.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            book.saleInfo.buyLink?.let { link ->
                val price = book.saleInfo.retailPrice
                Button(
                    onClick = { openBuyLink(context, link) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    if (price != null) {
                        Text("Buy for ${price.amount} ${price.currencyCode}")
                    } else {
                        Text("Buy Book")
                    }
                }
            }
        }
    }
}

@Composable
fun BookInfoRow(
    label: String, value: String, highlight: Boolean = false
) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.width(90.dp)
        )
        Text(
            text = value,
            style = if (highlight) MaterialTheme.typography.titleSmall
            else MaterialTheme.typography.bodyMedium,
            color = if (highlight) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurface
        )
    }
}

fun openBuyLink(context: Context, url: String) {

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)

}
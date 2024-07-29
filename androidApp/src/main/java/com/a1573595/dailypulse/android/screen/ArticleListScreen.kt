package com.a1573595.dailypulse.android.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.a1573595.dailypulse.article.ArticleViewModel
import com.a1573595.dailypulse.model.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListScreen(
    articleViewModel: ArticleViewModel,
    onAboutButtonClick: () -> Unit,
) {
    val articleUiState = articleViewModel.articleUiState.collectAsState()

    Column {
        TopAppBar(
            title = {
                Text("Articles")
            },
            actions = {
                IconButton(onClick = onAboutButtonClick) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "about",
                    )
                }
            },
        )
        when {
            articleUiState.value.isLoading -> LoaderView()
            articleUiState.value.error != null -> ErrorView(articleUiState.value.error!!)
            articleUiState.value.list.isEmpty() -> EmptyView()
            else -> ArticleListView(articleUiState.value.list)
        }
    }

//    when(articleViewModel.articlesState) {
//        is ArticleUiState.Success -> {
//            ArticleList(articleUiState.articles)
//        }
//    }
}

@Composable
fun LoaderView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxWidth(0.2f),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
fun ErrorView(throwable: Throwable) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = throwable.message.toString(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun EmptyView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Nothing to show",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun ArticleListView(articles: List<Article>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(articles) { article ->
            ArticleItem(article)
        }
    }
}

@Composable
fun ArticleItem(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = article.title,
            placeholder = rememberVectorPainter(image = Icons.Default.Refresh),
        )
        Spacer(
            modifier = Modifier.height(4.dp)
        )
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.desc,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.align(Alignment.End),
            text = article.date,
//            style = TextStyle(color = Color.Gray),
            style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray),
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LoaderViewPreview() {
    LoaderView()
}

@Preview(showBackground = true)
@Composable
fun ErrorViewPreview() {
    ErrorView(Exception("ErrorViewPreview"))
}

@Preview(showBackground = true)
@Composable
fun EmptyViewPreview() {
    EmptyView()
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
@Composable
fun ArticleItemPreview() {
    ArticleItem(
        Article(
            "Stock market today: Live updates - CNBC",
            "Futures were higher in premarket trading as Wall Street tried to regain its footing.",
            "2023-11-09",
            "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
        )
    )
}
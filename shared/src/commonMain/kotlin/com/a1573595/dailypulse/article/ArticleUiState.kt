package com.a1573595.dailypulse.article

import com.a1573595.dailypulse.model.Article

data class ArticleUiState(
    val loading: Boolean = true,
    val error: Throwable? = null,
    val list: List<Article> = emptyList(),
)

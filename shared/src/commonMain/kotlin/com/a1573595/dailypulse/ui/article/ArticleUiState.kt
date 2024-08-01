package com.a1573595.dailypulse.ui.article

import com.a1573595.dailypulse.data.model.Article

data class ArticleUiState(
    val isLoading: Boolean = true,
    val error: Throwable? = null,
    val list: List<Article> = emptyList(),
) {
    /// todo loading
    /// todo error
    /// todo data
}

//sealed interface ArticleUiState {
//    data object Loading : ArticleUiState
//    data class Error(val throwable: Throwable) : ArticleUiState
//    data class Success(val list: List<Article>) : ArticleUiState
//}
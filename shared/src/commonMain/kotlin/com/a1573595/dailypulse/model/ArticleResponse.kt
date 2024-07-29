package com.a1573595.dailypulse.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    /// todo 需要？
//    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResult: Int,
    @SerialName("articles")
    val articleList: List<ArticleRaw>
)

@Serializable
data class ArticleRaw(
    @SerialName("title")
    val title: String,
    /// description是NSObjectProtocol的參數
    @SerialName("description")
    val desc: String?,
    @SerialName("publishedAt")
    val date: String,
    @SerialName("urlToImage")
    val imageUrl: String?,
)

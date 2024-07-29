package com.a1573595.dailypulse.article

import com.a1573595.dailypulse.model.Article
import com.a1573595.dailypulse.model.ArticleRaw
import com.a1573595.dailypulse.network.ArticleApi
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticleUseCase(private val articleApi: ArticleApi) {
    suspend fun getArticleList(): List<Article> = articleApi.getArticleList().map { e -> mapArticleRawToArticle(e) }

    private fun mapArticleRawToArticle(articleRaw: ArticleRaw): Article = Article(
        title = articleRaw.title,
        desc = articleRaw.desc ?: "Click to find out more",
        date = getDaysAgoString(articleRaw.date),
        imageUrl = articleRaw.imageUrl ?: "https://via.placeholder.com/1920x1080",
    )

    private fun getDaysAgoString(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        return when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }
    }
}
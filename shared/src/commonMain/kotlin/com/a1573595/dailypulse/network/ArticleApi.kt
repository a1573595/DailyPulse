package com.a1573595.dailypulse.network

import com.a1573595.dailypulse.model.ArticleRaw
import com.a1573595.dailypulse.model.ArticleResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

fun interface ArticleApi {
    suspend fun getArticleList(): List<ArticleRaw>
}

class ArticleApiImpl(private val httpClient: HttpClient) : ArticleApi {
    private val country = "us"
    private val category = "business"
    private val apiKey = "f67ace1b27b24ce4b95c7f71fde88920"

    override suspend fun getArticleList(): List<ArticleRaw> =
        httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
            .body<ArticleResponse>().articleList
}
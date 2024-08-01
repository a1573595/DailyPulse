package com.a1573595.dailypulse.data.repository

import com.a1573595.dailypulse.data.model.ArticleRaw
import com.a1573595.dailypulse.data.network.ArticleApi

class ArticleRepository(
//    private val articleDao: ArticleDao,
    private val articleApi: ArticleApi,
) {
    suspend fun getArticleRawList(): List<ArticleRaw> = articleApi.getArticleRawList()
}
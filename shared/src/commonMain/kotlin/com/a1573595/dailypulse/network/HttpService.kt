package com.a1573595.dailypulse.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpService private constructor() {
    companion object {
        val instance: HttpService by lazy { HttpService() }
    }

    private val httpClient: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    val articleApi: ArticleApi = ArticleApiImpl(httpClient)
}
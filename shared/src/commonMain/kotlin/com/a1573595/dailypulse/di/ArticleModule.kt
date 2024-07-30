package com.a1573595.dailypulse.di

import com.a1573595.dailypulse.article.ArticleUseCase
import com.a1573595.dailypulse.article.ArticleViewModel
import com.a1573595.dailypulse.network.ArticleApi
import org.koin.dsl.module

val articlesModule = module {
    single<ArticleApi> { ArticleApi(get()) }
    single<ArticleUseCase> { ArticleUseCase(get()) }
    single<ArticleViewModel> { ArticleViewModel(get()) }
}
package com.a1573595.dailypulse.di

import com.a1573595.dailypulse.data.repository.ArticleRepository
import com.a1573595.dailypulse.ui.article.ArticleUseCase
import com.a1573595.dailypulse.ui.article.ArticleViewModel
import com.a1573595.dailypulse.data.network.ArticleApi
import org.koin.dsl.module

val articlesModule = module {
    single<ArticleApi> { ArticleApi(get()) }
    single<ArticleRepository> { ArticleRepository(get()) }
    single<ArticleUseCase> { ArticleUseCase(get()) }
    single<ArticleViewModel> { ArticleViewModel(get()) }
}
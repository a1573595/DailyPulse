package com.a1573595.dailypulse.di

import com.a1573595.dailypulse.ui.article.ArticleViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    val modules = sharedKoinModuleList

    startKoin {
        modules(modules)
    }
}

class ArticleInjector : KoinComponent {
    val articleViewModel: ArticleViewModel by inject()
}
package com.a1573595.dailypulse.android.di

import com.a1573595.dailypulse.article.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ArticleViewModel(get())
    }
}
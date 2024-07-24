//
//  ArticleListScreen.swift
//  iosApp
//
//  Created by 吳建儒 on 2024/7/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

extension ArticleListScreen {
    @MainActor
    class ArticleViewModelWrapper: ObservableObject {
        let articleViewModel: ArticleViewModel
        
        init() {
            articleViewModel = ArticleViewModel()
            articleUiState = articleViewModel.articleUiState.value
        }
        
        @Published var articleUiState: ArticleUiState
        
        func startObserving() {
            Task {
                for await articlesS in articleViewModel.articleUiState {
                    self.articleUiState = articlesS
                }
            }
        }
    }
}

struct ArticleListScreen: View {
    @ObservedObject private(set) var viewModel: ArticleViewModelWrapper
    
    var body: some View {
        VStack {
            AppBarView()
            
            if viewModel.articleUiState.isLoading {
                LoaderView()
            }
            else if let error = viewModel.articleUiState.error {
                ErrorView(message: String(describing: error))
            }
            else if viewModel.articleUiState.list.isEmpty {
                EmptyView()
            }
            else {
                ArticleListView(articleList: viewModel.articleUiState.list)
            }
        }.onAppear {
            viewModel.startObserving()
        }.onDisappear {
            
        }
    }
}

struct AppBarView: View {
    var body: some View {
        Text("Articles")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

struct LoaderView: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorView: View {
    let message: String
    
    var body: some View {
        Text(message).font(.title)
    }
}

struct EmptyView: View {
    var body: some View {
        Text("Nothing to show").font(.title)
    }
}

struct ArticleListView: View {
    let articleList: Array<Article>
    
    var body: some View {
        ScrollView {
            LazyVStack(spacing: 10) {
                ForEach(articleList, id: \.self) { article in
                    ArticleItemView(article: article)
                }
            }
        }
    }
}

struct ArticleItemView: View {
    let article: Article
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!.resizable().aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView()
                }
            }
            
            Text(article.title).font(.title).fontWeight(.bold)
            Text(article.desc)
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
        .padding(16)
    }
}

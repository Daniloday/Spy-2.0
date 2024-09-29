package com.missclick.spy.feature.words.di

import com.missclick.spy.feature.words.WordsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wordsModule = module {
    viewModel { WordsViewModel(get(), get(), get()) }
}
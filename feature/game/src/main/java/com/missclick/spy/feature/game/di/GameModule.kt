package com.missclick.spy.feature.game.di

import com.missclick.spy.feature.game.GameViewModel
import com.missclick.spy.feature.game.GameViewState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gameModule = module {
    viewModel { GameViewModel(get(), get()) }
}
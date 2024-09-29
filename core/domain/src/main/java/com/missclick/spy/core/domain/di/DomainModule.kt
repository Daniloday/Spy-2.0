package com.missclick.spy.core.domain.di

import com.missclick.spy.core.domain.AddCollectionUseCase
import com.missclick.spy.core.domain.GetCollectionsUseCase
import com.missclick.spy.core.domain.GetOptionsUseCase
import com.missclick.spy.core.domain.SetCollectionUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetCollectionsUseCase(get(), get()) }
    single { SetCollectionUseCase(get(), get()) }
    single { AddCollectionUseCase(get(), get()) }
    single { GetOptionsUseCase(get(), get(), get()) }
    single { GetOptionsUseCase(get(), get(), get()) }
}
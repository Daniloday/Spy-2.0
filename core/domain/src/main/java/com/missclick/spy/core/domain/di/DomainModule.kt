package com.missclick.spy.core.domain.di

import com.missclick.spy.core.domain.GetCollectionsUseCase
import com.missclick.spy.core.domain.GetRandomWordUseCase
import com.missclick.spy.core.domain.GetWordsUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetCollectionsUseCase(get(), get()) }
    single { GetWordsUseCase(get(), get(), get()) }
    single { GetRandomWordUseCase(get(), get(), get()) }
}
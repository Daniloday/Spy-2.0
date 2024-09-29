package com.missclick.spy.core.advertising.di

import com.missclick.spy.core.advertising.InterstitialAdManager
import com.missclick.spy.core.advertising.InterstitialAdManagerImpl
import org.koin.dsl.module

val advertisingModule = module {
    single<InterstitialAdManager> { InterstitialAdManagerImpl(get()) }
}
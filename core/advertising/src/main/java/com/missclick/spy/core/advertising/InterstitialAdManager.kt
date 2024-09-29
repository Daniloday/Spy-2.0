package com.missclick.spy.core.advertising

import android.app.Activity

interface InterstitialAdManager {
    fun showAd(activity: Activity, onAdClosed: () -> Unit)
}
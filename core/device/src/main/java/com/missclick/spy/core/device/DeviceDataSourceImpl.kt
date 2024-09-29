package com.missclick.spy.core.device

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import java.util.Locale

internal class DeviceDataSourceImpl(

) : DeviceDataSource {

    override suspend fun getCurrentLanguageCode(): String {
        return Locale.getDefault().language
    }

}
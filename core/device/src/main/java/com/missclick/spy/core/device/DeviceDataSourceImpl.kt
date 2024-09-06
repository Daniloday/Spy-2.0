package com.missclick.spy.core.device

import java.util.Locale

internal class DeviceDataSourceImpl(

) : DeviceDataSource {
    override suspend fun getCurrentLanguageCode(): String {
        return Locale.getDefault().language
    }
}
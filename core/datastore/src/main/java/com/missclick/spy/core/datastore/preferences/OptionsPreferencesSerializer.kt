package com.missclick.spy.core.datastore.preferences

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.missclick.spy.core.common.Constant.PLAYERS_DEFAULT
import com.missclick.spy.core.common.Constant.SPIES_DEFAULT
import com.missclick.spy.core.common.Constant.TIMER_DEFAULT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

internal class OptionsPreferencesSerializer(
    override val defaultValue: OptionsPreferences = OptionsPreferences(
        playersCount = PLAYERS_DEFAULT,
        spiesCount = SPIES_DEFAULT,
        time = TIMER_DEFAULT,
        collectionName = "",
        selectedLanguageCode = "",
        collectionLanguageCode = "",
    )
) : Serializer<OptionsPreferences> {

    override suspend fun readFrom(input: InputStream): OptionsPreferences =
        try {
            Json.decodeFromString(
                OptionsPreferences.serializer(), input.readBytes().decodeToString()
            )
        } catch (serialization: SerializationException) {
            throw CorruptionException("Unable to read Settings", serialization)
        }

    override suspend fun writeTo(t: OptionsPreferences, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(OptionsPreferences.serializer(), t)
                    .encodeToByteArray()
            )
        }
    }
}
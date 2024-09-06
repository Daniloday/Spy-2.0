package com.missclick.spy.core.datastore.preferences

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

internal class OptionsPreferencesSerializer(
    override val defaultValue: OptionsPreferences = OptionsPreferences(
        playersCount = 4,
        spiesCount = 1,
        time = 2,
        collectionName = "base"
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
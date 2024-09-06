package com.missclick.spy.core.data.repo

import com.missclick.spy.core.data.OptionsRepo
import com.missclick.spy.core.datastore.OptionsDataSource
import com.missclick.spy.core.model.Options
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class OptionsRepoImpl(
    private val optionsDataSourceImpl: OptionsDataSource
) : OptionsRepo {

    override val options: Flow<Options> = optionsDataSourceImpl.options

    override suspend fun setPlayersCount(playersCount: Int) {
        optionsDataSourceImpl.setPlayersCount(playersCount)
    }

    override suspend fun setSpiesCount(spiesCount: Int) {
        optionsDataSourceImpl.setSpiesCount(spiesCount)
    }

    override suspend fun setTime(time: Int) {
        optionsDataSourceImpl.setTime(time)
    }

    override suspend fun setCollectionName(collectionName: String) {
        optionsDataSourceImpl.setCollectionName(collectionName)
    }


}
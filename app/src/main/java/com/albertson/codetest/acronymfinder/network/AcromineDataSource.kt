package com.albertson.codetest.acronymfinder.network

import com.albertson.codetest.acronymfinder.network.model.AcromineApiResponseModel
import com.albertson.codetest.acronymfinder.network.model.AcromineApiResponseModelItem
import javax.inject.Inject

interface AcromineDataSource {
    suspend fun search(query: String): List<AcromineApiResponseModelItem>
}

class AcromineDataSourceImpl @Inject constructor(
    private val acromineApi: AcromineApi
): AcromineDataSource {

    override suspend fun search(query: String): List<AcromineApiResponseModelItem> {
        return acromineApi.search(query)
    }
}
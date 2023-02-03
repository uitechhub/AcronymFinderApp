package com.albertson.codetest.acronymfinder.network

import com.albertson.codetest.acronymfinder.network.model.AcromineApiResponseModel
import com.albertson.codetest.acronymfinder.network.model.AcromineApiResponseModelItem
import retrofit2.http.GET
import retrofit2.http.Query

interface AcromineApi {

    @GET("dictionary.py")
    suspend fun search(@Query("sf") query: String): List<AcromineApiResponseModelItem>
}
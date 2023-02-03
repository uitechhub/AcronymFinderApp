package com.albertson.codetest.acronymfinder.network

import com.albertson.codetest.acronymfinder.network.model.Lf
import javax.inject.Inject

interface AcromineRepository {
    suspend fun search(acronym: String): List<Lf>
}

class AcromineRepositoryImpl @Inject constructor(
    private val acromineDataSource: AcromineDataSource
): AcromineRepository {

    private val cache: Map<String, List<Lf>> = mapOf()

    override suspend fun search(acronym: String): List<Lf> {
        return if (cache.containsKey(acronym)) {
            cache[acronym]!!
        } else {
            val response = acromineDataSource.search(acronym).flatMap { item -> item.lfs }
            if (response.isNotEmpty()) {
                cache.map { acronym to response }
            }
            response
        }
    }

}


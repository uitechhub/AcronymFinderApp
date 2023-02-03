package com.albertson.codetest.acronymfinder

import com.albertson.codetest.acronymfinder.network.AcromineDataSource
import com.albertson.codetest.acronymfinder.network.AcromineRepository
import com.albertson.codetest.acronymfinder.network.AcromineRepositoryImpl
import com.albertson.codetest.acronymfinder.network.model.AcromineApiResponseModelItem
import com.albertson.codetest.acronymfinder.network.model.Lf
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AcromineRepositoryTest {

    private var repository: AcromineRepository = mockk()
    private var acromineDataSource: AcromineDataSource = mockk()

    @Before
    fun setup() {
        repository = AcromineRepositoryImpl(acromineDataSource)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `test repository search by query`() {
        val query = "fyi"
        val responseModel = sampleResponse(query)
        coEvery { acromineDataSource.search(query) } returns responseModel
        assertNotNull(responseModel)
        for (i in 0..3) {
            assertEquals(query, responseModel[0].lfs[i].longFormWord)
            assertEquals(100 * i, responseModel[0].lfs[i].freq)
            assertEquals(2023, responseModel[0].lfs[i].since)
        }
    }

    private fun sampleResponse(query: String): List<AcromineApiResponseModelItem> {
        val result = mutableListOf<AcromineApiResponseModelItem>()
        val lfResults = mutableListOf<Lf>()
        for (i in 0..3) {
            lfResults.add(Lf(freq = 100 * i, longFormWord = query, since = 2023, vars = listOf()))
        }
        result.add(AcromineApiResponseModelItem(lfResults, query))
        return result
    }
}
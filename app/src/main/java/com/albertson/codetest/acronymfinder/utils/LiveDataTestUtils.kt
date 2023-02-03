package com.albertson.codetest.acronymfinder.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.albertson.codetest.acronymfinder.network.ApiResource

fun <T> LiveData<T>.testObserver(): LiveDataTestObserver<T> {
    return LiveDataTestObserver<T>().also(this::observeForever)
}

class LiveDataTestObserver<T> : Observer<T> {
    private val _valueHistory = mutableListOf<T>()

    /**
     * List containing all the values changes to this livedata object
     * latest values will always be at the end of the list.
     */
    val valueHistory: List<T> get() = _valueHistory

    /**
     * Retrieves last value in value history.
     */
    val value: T?
        get() = _valueHistory.lastOrNull()

    override fun onChanged(t: T) {
        _valueHistory.add(t)
    }
}

object LiveDataTestUtils {

    /**
     * Test util function for [asserEquals] value and historical size of LiveData object
     * @param expectedValue final expected value to be stored in livedata object
     * @oaran testObserver [LiveDataTestObserver] of livedata object
     * @param numOfValues [testObserver] valueHistory size, default = 1
     */
    fun <T> assertHistorySizeAndValue(
        expectedValue: T, testObserver: LiveDataTestObserver<List<T>>, numOfValues: Int = 1
    ) {
        assert(numOfValues == testObserver.valueHistory.size)
        assert(expectedValue == testObserver.value)
    }


    fun <T> assertApiResourceValue(expected: ApiResource<T>, testObserver: LiveDataTestObserver<ApiResource<T>>) {
        assert(expected == testObserver.value)
    }
}

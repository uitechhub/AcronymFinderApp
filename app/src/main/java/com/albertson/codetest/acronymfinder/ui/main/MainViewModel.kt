package com.albertson.codetest.acronymfinder.ui.main

import android.content.res.Resources
import androidx.lifecycle.*
import com.albertson.codetest.acronymfinder.R
import com.albertson.codetest.acronymfinder.network.AcromineRepository
import com.albertson.codetest.acronymfinder.network.ApiResource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val resources: Resources,
    private val repository: AcromineRepository
) : ViewModel() {

    fun performSearch(acronym: String) = liveData(Dispatchers.IO) {
        emit(ApiResource.loading(null))
        try {
            emit(ApiResource.success(repository.search(acronym)))
        } catch (exception: Exception) {
            emit(ApiResource.error(null, exception.message ?: resources.getString(R.string.api_error_message)))
        }
    }
}
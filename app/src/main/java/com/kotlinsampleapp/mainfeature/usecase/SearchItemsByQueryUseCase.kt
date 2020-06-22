package com.kotlinsampleapp.mainfeature.usecase

import com.kotlinsampleapp.common.Result
import com.kotlinsampleapp.mainfeature.data.MainRepository
import com.kotlinsampleapp.mainfeature.model.SearchResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

/**
 * Use case that returns a flow instance that emits [Result] of [SearchResponse]
 * @author federico.giardini
 */
@ExperimentalCoroutinesApi
class SearchItemsByQueryUseCase(
    private val repository: MainRepository
) {
    fun execute(query: String): Flow<Result<SearchResponse>> {
        return repository.getItemsByQuery(query)
    }
}
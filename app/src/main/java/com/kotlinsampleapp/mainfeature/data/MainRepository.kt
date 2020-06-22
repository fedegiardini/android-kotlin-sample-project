package com.kotlinsampleapp.mainfeature.data

import android.util.Log
import com.kotlinsampleapp.common.LoadingStatus
import com.kotlinsampleapp.common.Result
import com.kotlinsampleapp.model.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

/**
 * @author federico.giardini
 */
@ExperimentalCoroutinesApi
class MainRepository(
    private val client: API
) {
    private var itemsOffset = 0
    private var query: String = ""

    fun getItemsByQuery(newQuery: String): Flow<Result<SearchResponse>> = flow {
        if (newQuery != query) itemsOffset = 0
        query = newQuery

        val isNewSearch = itemsOffset == 0

        if (isNewSearch) emit(Result.InProgress(LoadingStatus.NEW_SEARCH)) else emit(Result.InProgress(LoadingStatus.MORE_ITEMS))

        try {
            val response = client.getItems(query = newQuery, offset = itemsOffset)
            if (isNewSearch) emit(Result.Success(response)) else emit(Result.LoadMore(response))
            itemsOffset += response.results.size
        }
        catch (e: Exception) {
            Log.e("Error retrieving items", e.message)
            emit(Result.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}
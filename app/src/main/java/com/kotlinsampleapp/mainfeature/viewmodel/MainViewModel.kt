package com.kotlinsampleapp.mainfeature.viewmodel

import androidx.lifecycle.*
import com.kotlinsampleapp.common.LoadingStatus
import com.kotlinsampleapp.common.Result
import com.kotlinsampleapp.mainfeature.model.Product
import com.kotlinsampleapp.mainfeature.model.SearchResponse
import com.kotlinsampleapp.mainfeature.usecase.SearchItemsByQueryUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map

/**
 * ViewModel with logic to retrieve Product data from service
 *
 * @author federico.giardini
 */
@ExperimentalCoroutinesApi
class MainViewModel(
    private val searchItemsByQueryUseCase: SearchItemsByQueryUseCase
) : ViewModel() {

    private val searchQuery = MutableLiveData<String>()

    val searchResults: LiveData<SearchResponseViewState> = searchQuery.switchMap { query ->
        searchItemsByQueryUseCase.execute(query).map { result ->
            when (result) {
                is Result.InProgress -> {
                    if (result.status == LoadingStatus.NEW_SEARCH) {
                        SearchResponseViewState.Loading(true)
                    } else SearchResponseViewState.Loading(false)
                }
                is Result.Success -> SearchResponseViewState.Success(result.data)
                is Result.LoadMore -> SearchResponseViewState.LoadMore(result.data.results)
                is Result.Error -> SearchResponseViewState.Failed(result.exception)
            }
        }.asLiveData()
    }

    /**
     * Sets a new value to [searchQuery] that triggers a backend call to retrieve products
     *
     * @param query value of the new query
     */
    fun newSearch(query: String) {
        searchQuery.value = query
    }

    /**
     * Triggers a backend call to retrieve products using an already set query
     * Should be called only if items are already loaded using [newSearch]
     */
    fun loadMoreItems() {
        searchQuery.value = searchQuery.value
    }

    sealed class SearchResponseViewState {
        data class Loading(val newSearch: Boolean) : SearchResponseViewState()
        data class Failed(val exception: Exception) : SearchResponseViewState()
        data class Success(val data: SearchResponse) : SearchResponseViewState()
        data class LoadMore(val data: List<Product>) : SearchResponseViewState()
    }
}
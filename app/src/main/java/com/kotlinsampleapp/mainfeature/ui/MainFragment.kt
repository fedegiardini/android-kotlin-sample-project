package com.kotlinsampleapp.mainfeature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kotlinsampleapp.R
import com.kotlinsampleapp.common.hide
import com.kotlinsampleapp.common.show
import com.kotlinsampleapp.mainfeature.adapter.ProductsAdapter
import com.kotlinsampleapp.mainfeature.listener.ProductSelectionListener
import com.kotlinsampleapp.mainfeature.model.Product
import com.kotlinsampleapp.mainfeature.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.kotlinsampleapp.mainfeature.viewmodel.MainViewModel.SearchResponseViewState
import com.kotlinsampleapp.util.ListScrollHelper
import com.kotlinsampleapp.util.ListScrollHelper.ScrollListener
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * @author federico.giardini
 */
@ExperimentalCoroutinesApi
class MainFragment : Fragment() {
    private lateinit var navController: NavController
    private val recyclerViewAdapter: ProductsAdapter by lazy {
        ProductsAdapter()
    }
    private lateinit var listScrollHelper: ListScrollHelper
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        itemList.adapter = recyclerViewAdapter

        viewModel.searchResults.observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState) {
                is SearchResponseViewState.Loading -> showLoading(viewState.newSearch)
                is SearchResponseViewState.Success -> displayItems(
                    viewState.data.results,
                    viewState.data.paging.total
                )
                is SearchResponseViewState.LoadMore -> updateItems(viewState.data)
                is SearchResponseViewState.Failed -> displayError()
            }
        })

        searchBar.setOnEditorActionListener { v, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.newSearch(v.text.toString())
                    true
                }
                else -> false
            }
        }

        listScrollHelper = ListScrollHelper()
        listScrollHelper.scrollListener = object : ScrollListener {
            override fun onScrolledDown() {
            }

            override fun onTopReached() {
            }

            override fun onLoadRequested() {
                viewModel.loadMoreItems()
            }
        }
        itemList.addOnScrollListener(listScrollHelper)

        recyclerViewAdapter.productSelectionListener = object : ProductSelectionListener {
            override fun onProductSelected(product: Product) {
                // set navigation to product details
                /*
                findNavController().navigate()
                 */
            }
        }
    }

    private fun showLoading(isNewSearch: Boolean = true) {
        if (isNewSearch) {
            itemList.hide()
            resultsSize.hide()
        }
        progressbar.show()
        errorMessage.hide()
    }

    private fun displayItems(items: List<Product>, resultsAmount: Int) {
        if (resultsAmount == 1) {
            resultsSize.text = getString(R.string.result_found)
        } else {
            resultsSize.text = getString(R.string.results_found, resultsAmount)
        }
        recyclerViewAdapter.setItemList(items)
        listScrollHelper.clearLoading()
        itemList.show()
        resultsSize.show()
        errorMessage.hide()
        progressbar.hide()
    }

    private fun updateItems(items: List<Product>) {
        recyclerViewAdapter.updateItems(items)
        listScrollHelper.clearLoading()
        itemList.show()
        resultsSize.show()
        errorMessage.hide()
        progressbar.hide()
    }

    private fun displayError() {
        errorMessage.show()
        itemList.hide()
        progressbar.hide()
        resultsSize.hide()
    }
}
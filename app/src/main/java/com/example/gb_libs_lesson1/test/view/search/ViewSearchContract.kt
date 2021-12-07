package com.example.gb_libs_lesson1.test.view.search

import com.example.gb_libs_lesson1.test.model.SearchResult
import com.example.gb_libs_lesson1.test.view.ViewContract

internal interface ViewSearchContract : ViewContract {
    fun displaySearchResults(
        searchResults: List<SearchResult>,
        totalCount: Int
    )

    fun displayError()
    fun displayError(error: String)
    fun displayLoading(show: Boolean)
}

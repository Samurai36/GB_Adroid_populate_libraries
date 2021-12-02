package com.example.gb_libs_lesson1.test.view

import com.example.gb_libs_lesson1.test.model.SearchResult

internal interface ViewContract {
    fun displaySearchResults(
        searchResults: List<SearchResult>,
        totalCount: Int
    )

    fun displayError()
    fun displayError(error: String)
    fun displayLoading(show: Boolean)
}

package com.example.gb_libs_lesson1.test.presenter.search

import com.example.gb_libs_lesson1.test.presenter.PresenterContract
import com.example.gb_libs_lesson1.test.view.ViewContract

internal interface PresenterSearchContract : PresenterContract {
    fun searchGitHub(searchQuery: String)
    fun onAttach(view : ViewContract)
    fun onDetach()
}

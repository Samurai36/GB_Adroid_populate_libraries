package com.example.gb_libs_lesson1.test.presenter.details

import com.example.gb_libs_lesson1.test.presenter.PresenterContract

internal interface PresenterDetailsContract : PresenterContract {
    fun setCounter(count: Int)
    fun onIncrement()
    fun onDecrement()
}

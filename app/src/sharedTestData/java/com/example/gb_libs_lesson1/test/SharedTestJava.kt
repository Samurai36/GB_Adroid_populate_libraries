package com.example.gb_libs_lesson1.test

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.gb_libs_lesson1.R

internal const val TEST_NUMBER_OF_RESULTS_ZERO = "Number of results: 0"
internal const val TEST_NUMBER_OF_RESULTS_PLUS_1 = "Number of results: 1"
internal const val TEST_NUMBER_OF_RESULTS_MINUS_1 = "Number of results: -1"

internal fun loadList() {
    Espresso.onView(ViewMatchers.withId(R.id.searchEditText)).perform(ViewActions.click())
    Espresso.onView(ViewMatchers.withId(R.id.searchEditText))
        .perform(ViewActions.replaceText("algol"), ViewActions.closeSoftKeyboard())
    Espresso.onView(ViewMatchers.withId(R.id.searchEditText))
        .perform(ViewActions.pressImeActionButton())
}
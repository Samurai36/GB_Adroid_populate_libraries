package com.example.gb_libs_lesson1.test.view.search


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.gb_libs_lesson1.R
import com.example.gb_libs_lesson1.test.TEST_NUMBER_OF_RESULTS_MINUS_1
import com.example.gb_libs_lesson1.test.TEST_NUMBER_OF_RESULTS_PLUS_1
import com.example.gb_libs_lesson1.test.TEST_NUMBER_OF_RESULTS_ZERO
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainTestActivityTestWithEspressoTestRecorder {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainTestActivity::class.java)

    @Test
    fun mainTestActivityTestWithEspressoTestRecorder() {

        //находим кнопку по тексту и позиции с проверкой что она отображается
        val materialButton = onView(
            allOf(
                withId(R.id.toDetailsActivityButton), withText("to details"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        // нажимаем на кнопку
        materialButton.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.totalCountTextView), withText(TEST_NUMBER_OF_RESULTS_ZERO),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        // проверяем, что текст в текствью совпадает с ожидаемым текстом
        textView.check(matches(withText(TEST_NUMBER_OF_RESULTS_ZERO)))

        val materialButton2 = onView(
            allOf(
                withId(R.id.incrementButton), withText("+"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        textView.check(matches(withText(TEST_NUMBER_OF_RESULTS_PLUS_1)))

        val materialButton3 = onView(
            allOf(
                withId(R.id.decrementButton), withText("-"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        materialButton3.perform(click())

        textView.check(matches(withText(TEST_NUMBER_OF_RESULTS_MINUS_1)))

        // нажимаем кнопку "назад"
        pressBack()

        val appCompatEditText = onView(
            allOf(
                withId(R.id.searchEditText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(click())

        // в поле edittext вбиваем тест, закрываем клавиатуру
        appCompatEditText.perform(replaceText("testSearch"), closeSoftKeyboard())
        // нажимаем кнопку действия для поиска
        appCompatEditText.perform(pressImeActionButton())

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}

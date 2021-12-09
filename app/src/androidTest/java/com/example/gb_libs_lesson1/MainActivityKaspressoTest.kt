package com.example.gb_libs_lesson1

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_libs_lesson1.screen.MainTestScreen
import com.example.gb_libs_lesson1.test.view.search.MainTestActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityKaspressoTest : TestCase() {

    private lateinit var scenario: ActivityScenario<MainTestActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainTestActivity::class.java)
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            junit.framework.TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        junit.framework.TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun test() =
        run {

            step("toDetailsActivityButton has text") {
                MainTestScreen {
                    toDetailsActivityButton {
                        flakySafely(timeoutMs = 7000) { isVisible() }
                        hasText("TO DETAILS")

                    }
                }
            }

            step("searchEditText click") {
                MainTestScreen {
                    searchEditText {
                        click()
                    }
                }
            }

            step("searchEditText set text") {
                MainTestScreen {
                    searchEditText {
                        typeText("algol")
                        Espresso.closeSoftKeyboard()
                        pressImeAction()
                    }
                    Espresso.onView(ViewMatchers.isRoot()).perform(delay())
                    totalCountTextView {
                        flakySafely(timeoutMs = 7000) { isVisible() }
                        hasText("Number of results: 2702")
                    }
                }
            }

        }


    private fun delay(): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()
            override fun getDescription(): String = "wait for $2 seconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(2000)
            }
        }
    }


    @After
    fun close() {
        scenario.close()
    }
}
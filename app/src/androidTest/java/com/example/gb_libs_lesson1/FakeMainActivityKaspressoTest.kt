package com.example.gb_libs_lesson1

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_libs_lesson1.screen.MainTestScreen
import com.example.gb_libs_lesson1.test.view.search.FakeMainTestActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FakeMainActivityKaspressoTest : TestCase() {

    private lateinit var scenario: ActivityScenario<FakeMainTestActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(FakeMainTestActivity::class.java)
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
                        this@MainTestScreen.closeSoftKeyboard()
                        pressImeAction()
                    }
                    totalCountTextView {
                        flakySafely(timeoutMs = 7000) { isVisible() }
                        hasText("Number of results: 42")
                    }
                }
            }

        }

    @After
    fun close() {
        scenario.close()
    }
}
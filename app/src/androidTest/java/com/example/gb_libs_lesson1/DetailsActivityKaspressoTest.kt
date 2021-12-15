package com.example.gb_libs_lesson1

import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_libs_lesson1.screen.DetailsScreen
import com.example.gb_libs_lesson1.test.TEST_NUMBER_OF_RESULTS_MINUS_1
import com.example.gb_libs_lesson1.test.TEST_NUMBER_OF_RESULTS_ZERO
import com.example.gb_libs_lesson1.test.view.details.DetailsActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsActivityKaspressoTest : TestCase() {

    private lateinit var scenario: ActivityScenario<DetailsActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(DetailsActivity::class.java)
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityTextView_NotNull() {
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            assertNotNull(totalCountTextView)
        }
    }

    @Test
    fun test() =
        run {

            step("Check TextView is displayed") {
                DetailsScreen {
                    totalCountTextView {
                        isDisplayed()
                    }
                }
            }

            step("Check TextView is completelyDisplayed") {
                DetailsScreen {
                    totalCountTextView {
                        isCompletelyDisplayed()
                    }
                }
            }

            step("Check TextView has text") {
                DetailsScreen {
                    totalCountTextView {
                        hasText(TEST_NUMBER_OF_RESULTS_ZERO)
                    }
                }
            }

            step("buttons is visible") {
                DetailsScreen {
                    incrementButton {
                        isVisible()
                    }
                    decrementButton {
                        isVisible()
                    }
                }
            }

            step("button decrement is working") {
                DetailsScreen {
                    decrementButton {
                        click()
                    }
                    totalCountTextView {
                        flakySafely(timeoutMs = 7000) { isVisible() }
                        hasText(TEST_NUMBER_OF_RESULTS_MINUS_1)
                    }
                }
            }

            step("button increment is working") {
                DetailsScreen {
                    incrementButton {
                        click()
                    }
                    totalCountTextView {
                        flakySafely(timeoutMs = 7000) { isVisible() }
                        hasText(TEST_NUMBER_OF_RESULTS_ZERO)
                    }
                }
            }

        }

    @After
    fun close() {
        scenario.close()
    }
}

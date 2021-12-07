package com.example.gb_libs_lesson1

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_libs_lesson1.test.view.search.MainTestActivity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainTestActivityTest {

    private lateinit var scenario: ActivityScenario<MainTestActivity>
    private lateinit var context: Context

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainTestActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            assertNotNull(it)
        }
    }

    @Test
    fun activityIsVisibleButton_Test(){
        scenario.onActivity {
            val detailsButton = it.findViewById<Button>(R.id.toDetailsActivityButton)
            Assert.assertTrue(detailsButton.isVisible)
        }
    }

    @Test
    fun activityIsVisibleEditText_Test(){
        scenario.onActivity {
            val totalCountEditText = it.findViewById<EditText>(R.id.searchEditText)
            Assert.assertTrue(totalCountEditText.isVisible)
        }
    }

    @Test
    fun activity_IsResumed() {
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityEditText_NotNull() {
        scenario.onActivity {
            val totalCountEditText = it.findViewById<EditText>(R.id.searchEditText)
            assertNotNull(totalCountEditText)
        }
    }

    @Test
    fun activityEditText_SetText() {
        scenario.onActivity {
            val totalCountEditText = it.findViewById<EditText>(R.id.searchEditText)
            totalCountEditText.setText("text_test", TextView.BufferType.EDITABLE)
            assertNotNull(totalCountEditText.text)
            assertEquals("text_test", totalCountEditText.text.toString())
        }
    }

    @Test
    fun activityEditText_IsVisible() {
        scenario.onActivity {
            val totalCountEditText = it.findViewById<EditText>(R.id.searchEditText)
            assertEquals(View.VISIBLE, totalCountEditText.visibility)
        }
    }


    @After
    fun close() {
        scenario.close()
    }
}

package com.example.gb_libs_lesson1.screen

import com.example.gb_libs_lesson1.R
import com.example.gb_libs_lesson1.test.view.search.MainTestActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object MainTestScreen : KScreen<MainTestScreen>() {

    override val layoutId: Int? = R.layout.activitytest_main
    override val viewClass: Class<*>? = MainTestActivity::class.java

    val toDetailsActivityButton = KButton { withId(R.id.toDetailsActivityButton) }

    val searchEditText = KEditText { withId(R.id.searchEditText) }

    val totalCountTextView = KTextView { withId(R.id.totalCountTextView) }
}

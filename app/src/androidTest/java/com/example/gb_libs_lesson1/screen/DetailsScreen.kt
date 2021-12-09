package com.example.gb_libs_lesson1.screen

import com.example.gb_libs_lesson1.R
import com.example.gb_libs_lesson1.test.view.details.DetailsActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.check.KCheckBox
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object DetailsScreen : KScreen<DetailsScreen>() {

    override val layoutId: Int? = R.layout.activity_details
    override val viewClass: Class<*>? = DetailsActivity::class.java

    val decrementButton = KButton { withId(R.id.decrementButton) }

    val incrementButton = KButton { withId(R.id.incrementButton) }

    val totalCountTextView = KTextView { withId(R.id.totalCountTextView) }

    val checkBox = KCheckBox { withId(R.id.checkBox) }
}

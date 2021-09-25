package com.example.gb_libs_lesson1.mvp.view.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView: MvpView {
    fun init()
    fun updateList()
}
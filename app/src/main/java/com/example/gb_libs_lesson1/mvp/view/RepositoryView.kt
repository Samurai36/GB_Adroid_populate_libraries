package com.example.gb_libs_lesson1.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface RepositoryView : MvpView {
    fun init()
    fun setName(name: String)
    fun setFullName(fullName: String)
    fun setDescription(description: String)
}

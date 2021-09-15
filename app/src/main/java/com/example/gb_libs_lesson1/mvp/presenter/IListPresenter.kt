package com.example.gb_libs_lesson1.mvp.presenter

import com.example.gb_libs_lesson1.mvp.view.IItemView
import com.example.gb_libs_lesson1.mvp.view.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(View: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
package com.example.gb_libs_lesson1.mvp.view

interface UserItemView : IItemView {
    fun showLogin(login: String)
    fun showAvatar(url: String)
}
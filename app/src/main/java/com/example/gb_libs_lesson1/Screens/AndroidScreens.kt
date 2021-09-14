package com.example.gb_libs_lesson1.Screens

import com.example.gb_libs_lesson1.mvp.model.GithubUser
import com.example.gb_libs_lesson1.mvp.view.ui.UserFragment
import com.example.gb_libs_lesson1.mvp.view.ui.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class UsersScreen : SupportAppScreen() {

        override fun getFragment() = UsersFragment()
    }

    class UserScreen(private val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }
}
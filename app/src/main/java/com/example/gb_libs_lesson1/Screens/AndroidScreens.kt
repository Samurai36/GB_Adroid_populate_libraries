package com.example.gb_libs_lesson1.Screens

import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubRepository
import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import com.example.gb_libs_lesson1.mvp.view.ui.fragments.UserFragment
import com.example.gb_libs_lesson1.mvp.view.ui.fragments.UserRepositoriesFragment
import com.example.gb_libs_lesson1.mvp.view.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class UsersScreen : SupportAppScreen() {

        override fun getFragment() = UsersFragment()
    }

    class UserScreen(private val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }

    class RepositoryScreen(private val user: GithubRepository) : SupportAppScreen() {
        override fun getFragment() = UserRepositoriesFragment.newInstance(user)
    }

}
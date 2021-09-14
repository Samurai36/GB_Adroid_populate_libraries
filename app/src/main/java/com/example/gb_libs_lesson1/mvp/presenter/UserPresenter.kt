package com.example.gb_libs_lesson1.mvp.presenter

import com.example.gb_libs_lesson1.mvp.model.GithubUser
import com.example.gb_libs_lesson1.mvp.view.ui.UserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(
    private val user: GithubUser,
    private val router: Router
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}
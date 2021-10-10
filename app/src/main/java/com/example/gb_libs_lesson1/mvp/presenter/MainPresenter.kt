package com.example.gb_libs_lesson1.mvp.presenter

import com.example.gb_libs_lesson1.Screens.AndroidScreens
import com.example.gb_libs_lesson1.mvp.view.ui.MainView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter @Inject constructor(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.UsersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}


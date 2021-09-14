package com.example.gb_libs_lesson1.mvp.presenter

import com.example.gb_libs_lesson1.Screens.AndroidScreens
import com.example.gb_libs_lesson1.MainView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.UsersScreen())
    }

    fun backPressed(){
        router.exit()
    }
}


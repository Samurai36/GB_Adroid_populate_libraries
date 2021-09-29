package com.example.gb_libs_lesson1

import android.app.Application
import com.example.gb_libs_lesson1.mvp.model.GithubDatabase.Companion.create
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App : Application() {

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigationHolder get() = cicerone.navigatorHolder
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this

        create(this)
    }

    companion object {

        lateinit var instance: App
    }
}
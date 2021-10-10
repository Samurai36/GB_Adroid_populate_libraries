package com.example.gb_libs_lesson1

import android.app.Application
import com.example.gb_libs_lesson1.di.components.AppComponent
import com.example.gb_libs_lesson1.di.components.DaggerAppComponent
import com.example.gb_libs_lesson1.di.components.UserRepositorySubcomponent
import com.example.gb_libs_lesson1.di.components.UsersSubcomponent
import com.example.gb_libs_lesson1.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    var usersSubcomponent: UsersSubcomponent? = null
        private set

    var userRepositorySubcomponent: UserRepositorySubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUsersSubcomponent() = appComponent.usersSubcomponent().also {
        usersSubcomponent = it
    }

    fun initUserRepositorySubcomponent() =
        appComponent.usersSubcomponent().userRepositorySubcomponent().also {
            userRepositorySubcomponent = it
        }

    fun releaseUsersScope() {
        usersSubcomponent = null
    }
    fun releaseUserReposScope() {
        userRepositorySubcomponent = null
    }

    companion object {
        lateinit var instance: App
    }
}
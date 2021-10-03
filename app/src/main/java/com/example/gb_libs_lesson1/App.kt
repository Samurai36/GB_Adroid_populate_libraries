package com.example.gb_libs_lesson1

import android.app.Application
import com.example.gb_libs_lesson1.di.components.AppComponent
import com.example.gb_libs_lesson1.di.components.DaggerAppComponent
import com.example.gb_libs_lesson1.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}
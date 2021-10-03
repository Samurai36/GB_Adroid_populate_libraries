package com.example.gb_libs_lesson1.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class CiceroneModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun cicerone(): Cicerone<Router> = cicerone

    @Provides
    @Singleton
    fun navigationHolder(): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @Singleton
    fun router(): Router = cicerone.router
}
package com.example.gb_libs_lesson1.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Named
import javax.inject.Singleton

@Module
class SchedulerModule {


    @Provides
    @Singleton
    @Named("schedulerIo")
    fun schedulerIo(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    @Named("schedulerMain")
    fun schedulerMain(): Scheduler = AndroidSchedulers.mainThread()

}
package com.example.gb_libs_lesson1.di.components

import com.example.gb_libs_lesson1.MainActivity
import com.example.gb_libs_lesson1.di.modules.*
import com.example.gb_libs_lesson1.mvp.presenter.MainPresenter
import com.example.gb_libs_lesson1.mvp.presenter.RepositoryPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DbModule::class,
        CiceroneModule::class,
        SchedulerModule::class
    ]
)
interface AppComponent {

    fun usersSubcomponent(): UsersSubcomponent

    fun presenter(): MainPresenter

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(repositoryPresenter: RepositoryPresenter)

}
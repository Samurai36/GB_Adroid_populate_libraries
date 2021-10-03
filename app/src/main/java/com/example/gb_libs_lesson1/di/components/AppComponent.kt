package com.example.gb_libs_lesson1.di.components

import com.example.gb_libs_lesson1.MainActivity
import com.example.gb_libs_lesson1.di.modules.*
import com.example.gb_libs_lesson1.mvp.presenter.MainPresenter
import com.example.gb_libs_lesson1.mvp.presenter.RepositoryPresenter
import com.example.gb_libs_lesson1.mvp.presenter.UserPresenter
import com.example.gb_libs_lesson1.mvp.presenter.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class
    ]
)
interface AppComponent {

    fun presenter(): MainPresenter

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)

}
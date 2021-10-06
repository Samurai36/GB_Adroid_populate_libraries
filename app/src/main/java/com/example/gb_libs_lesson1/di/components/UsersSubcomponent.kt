package com.example.gb_libs_lesson1.di.components

import com.example.gb_libs_lesson1.di.modules.UsersModule
import com.example.gb_libs_lesson1.di.scopes.UsersScope
import com.example.gb_libs_lesson1.mvp.presenter.UsersPresenter
import dagger.Subcomponent

@UsersScope
@Subcomponent(
    modules = [UsersModule::class]
)
interface UsersSubcomponent {

    fun userRepositorySubcomponent(): UserRepositorySubcomponent

    fun inject(usersPresenter: UsersPresenter)
}
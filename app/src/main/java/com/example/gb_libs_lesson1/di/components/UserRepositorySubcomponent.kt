package com.example.gb_libs_lesson1.di.components

import com.example.gb_libs_lesson1.di.modules.UserRepositoryModule
import com.example.gb_libs_lesson1.di.scopes.UserRepositoryScope
import com.example.gb_libs_lesson1.mvp.presenter.UserPresenter
import dagger.Subcomponent

@UserRepositoryScope
@Subcomponent(
    modules = [UserRepositoryModule::class]
)
interface UserRepositorySubcomponent {
    fun inject(userPresenter: UserPresenter)
}
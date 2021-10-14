package com.example.gb_libs_lesson1.di.modules

import com.example.gb_libs_lesson1.di.scopes.UsersScope
import com.example.gb_libs_lesson1.mvp.model.*
import com.example.gb_libs_lesson1.mvp.model.network.INetworkStatus
import com.example.gb_libs_lesson1.mvp.model.remote.IApiHolder
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

@Module
class UsersModule {

    @Provides
    @UsersScope
    fun getUsersCache(db: GithubDatabase
    ): IGithubUserCache {
        return GithubUserCache(db)
    }

    @Provides
    @UsersScope
    fun usersRepo(
        networkStatus: INetworkStatus,
        cache: IGithubUserCache,
        apiHolder: IApiHolder
    ): IGithubUsersRepo {
        return GithubUsersRepo(networkStatus, cache, apiHolder)
    }
}
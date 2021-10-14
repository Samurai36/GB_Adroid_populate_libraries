package com.example.gb_libs_lesson1.di.modules

import com.example.gb_libs_lesson1.di.scopes.UserRepositoryScope
import com.example.gb_libs_lesson1.mvp.model.*
import com.example.gb_libs_lesson1.mvp.model.network.AndroidNetworkStatus
import com.example.gb_libs_lesson1.mvp.model.network.INetworkStatus
import com.example.gb_libs_lesson1.mvp.model.remote.IApiHolder
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

@Module
class UserRepositoryModule {

    @Provides
    @UserRepositoryScope
    fun getUserReposCache(
        db: GithubDatabase,
        @Named("schedulerIo") schedulerUI: Scheduler
    ): IGithubRepositoriesCache {
        return GithubRepositoriesCache(db, schedulerUI)
    }

    @Provides
    @UserRepositoryScope
    fun repositoriesRepo(
        networkStatus: INetworkStatus,
        cache: IGithubRepositoriesCache,
        apiHolder: IApiHolder,
        @Named("schedulerIo") schedulerUI: Scheduler
    ): IGithubRepositoriesRepo {
        return GithubRepositoriesRepo(networkStatus, cache, apiHolder, schedulerUI)
    }

}
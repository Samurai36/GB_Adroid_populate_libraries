package com.example.gb_libs_lesson1.mvp.model

import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import com.example.gb_libs_lesson1.mvp.model.network.INetworkStatus
import com.example.gb_libs_lesson1.mvp.model.remote.IApiHolder
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Named

class GithubUsersRepo(
    private val networkStatus: INetworkStatus,
    private val cache: IGithubUserCache,
    private val apiHolder: IApiHolder
) : IGithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                apiHolder.apiService.getUsers()
                    .flatMap { users ->
                        cache.putUsers(users).toSingleDefault(users)
                    }
            } else {
                cache.getUsers()
            }
        }.subscribeOn(Schedulers.io())
}
package com.example.gb_libs_lesson1.mvp.model

import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import com.example.gb_libs_lesson1.mvp.model.network.INetworkStatus
import com.example.gb_libs_lesson1.mvp.model.remote.ApiHolder
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo(private val networkStatus: INetworkStatus, val cache: IGithubUserCache) {

    fun getUsers(): Single<List<GithubUser>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            ApiHolder.apiService.getUsers()
                .flatMap { users ->
                    cache.putUsers(users).toSingleDefault(users)
                }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}
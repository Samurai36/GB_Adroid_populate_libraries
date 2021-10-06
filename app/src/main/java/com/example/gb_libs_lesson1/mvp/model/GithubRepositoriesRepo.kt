package com.example.gb_libs_lesson1.mvp.model

import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubRepository
import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import com.example.gb_libs_lesson1.mvp.model.network.INetworkStatus
import com.example.gb_libs_lesson1.mvp.model.remote.IApiHolder
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GithubRepositoriesRepo @Inject constructor(
    private val networkStatus: INetworkStatus,
    private val cache: IGithubRepositoriesCache,
    private val apiHolder: IApiHolder
) : IGithubRepositoriesRepo {

    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposURL?.let { url ->
                    apiHolder.apiService.getUserRepositories(url).flatMap { repositories ->
                        user.login?.let {
                            cache.putUserRepos(user, repositories).toSingleDefault(repositories)
                        } ?: error("No find user in cache")
                    }
                } ?: Single.error<List<GithubRepository>>(RuntimeException("User hasn't repos url"))
                    .subscribeOn(Schedulers.io())

            } else {
                cache.getUserRepos(user)
            }
        }.subscribeOn(Schedulers.io())
}
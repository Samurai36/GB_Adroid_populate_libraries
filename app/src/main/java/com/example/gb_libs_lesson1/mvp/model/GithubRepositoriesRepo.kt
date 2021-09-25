package com.example.gb_libs_lesson1.mvp.model

import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubRepository
import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import com.example.gb_libs_lesson1.mvp.model.remote.ApiHolder
import com.example.gb_libs_lesson1.mvp.model.remote.GithubUsersService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class GithubRepositoriesRepo(val api: GithubUsersService) {

    fun getRepositories(user: GithubUser): Single<List<GithubRepository>> =
        user.reposURL?.let {
            ApiHolder.apiService.getUserRepositories(it).subscribeOn(Schedulers.io())
        } ?: Single.error<List<GithubRepository>>(RuntimeException("don't have repo"))
            .subscribeOn(Schedulers.io())

}
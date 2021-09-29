package com.example.gb_libs_lesson1.mvp.model

import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubRepository
import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {

    fun getUserRepos(user: GithubUser): Single<List<GithubRepository>>
    fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>): Completable
}
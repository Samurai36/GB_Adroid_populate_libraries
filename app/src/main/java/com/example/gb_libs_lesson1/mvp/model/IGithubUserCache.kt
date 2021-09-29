package com.example.gb_libs_lesson1.mvp.model

import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubUserCache {

    fun getUsers(): Single<List<GithubUser>>

    fun putUsers(users: List<GithubUser>): Completable

}
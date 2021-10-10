package com.example.gb_libs_lesson1.mvp.model.remote

import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubRepository
import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface GithubUsersService {

    @GET("/users")
    fun getUsers() : Single<List<GithubUser>>

    @GET
    fun getUserRepositories(@Url url: String): Single<List<GithubRepository>>
}
package com.example.gb_libs_lesson1.mvp.model

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    private val users = listOf(
        GithubUser("User1"),
        GithubUser("User2"),
        GithubUser("User3"),
        GithubUser("User4"),
        GithubUser("User5"),
        GithubUser("User6")
    )

    fun getUsers() = Observable.create<List<GithubUser>>
    { emitter ->
        emitter.onNext(users)
        emitter.onComplete()
    }

}
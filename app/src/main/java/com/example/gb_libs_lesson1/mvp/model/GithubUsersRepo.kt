package com.example.gb_libs_lesson1.mvp.model

class GithubUsersRepo {
    private val users = listOf(
        GithubUser("User1"),
        GithubUser("User2"),
        GithubUser("User3"),
        GithubUser("User4"),
        GithubUser("User5"),
        GithubUser("User6")
    )
    fun getUsers() = users
}
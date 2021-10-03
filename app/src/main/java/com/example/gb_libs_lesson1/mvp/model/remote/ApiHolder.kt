package com.example.gb_libs_lesson1.mvp.model.remote

import javax.inject.Inject

interface IApiHolder {
    val apiService: GithubUsersService
}

class ApiHolder @Inject constructor(override val apiService: GithubUsersService) : IApiHolder
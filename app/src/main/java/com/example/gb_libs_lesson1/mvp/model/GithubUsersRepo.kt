package com.example.gb_libs_lesson1.mvp.model

import com.example.gb_libs_lesson1.mvp.model.remote.ApiHolder

class GithubUsersRepo {

    fun getUsers() = ApiHolder.apiService.getUsers()

}
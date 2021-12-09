package com.example.gb_libs_lesson1.test.presenter

import com.example.gb_libs_lesson1.test.repository.RepositoryCallback

internal interface RepositoryContract {
    fun searchGithub(
        query: String,
        callback: RepositoryCallback
    )
}

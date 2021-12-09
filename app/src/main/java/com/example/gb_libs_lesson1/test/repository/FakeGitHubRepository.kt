package com.example.gb_libs_lesson1.test.repository

import com.example.gb_libs_lesson1.test.model.SearchResponse
import com.example.gb_libs_lesson1.test.presenter.RepositoryContract
import retrofit2.Response

internal class FakeGitHubRepository : RepositoryContract {

    override fun searchGithub(
        query: String,
        callback: RepositoryCallback
    ) {
        callback.handleGitHubResponse(Response.success(SearchResponse(42, listOf())))
    }
}

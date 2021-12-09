package com.example.gb_libs_lesson1.test.repository

import com.example.gb_libs_lesson1.test.model.SearchResponse
import retrofit2.Response

interface RepositoryCallback {
    fun handleGitHubResponse(response: Response<SearchResponse?>?)
    fun handleGitHubError()
}

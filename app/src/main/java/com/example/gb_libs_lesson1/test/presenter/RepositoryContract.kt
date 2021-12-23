package com.example.gb_libs_lesson1.test.presenter

import com.example.gb_libs_lesson1.test.model.SearchResponse
import com.example.gb_libs_lesson1.test.repository.RepositoryCallback
import io.reactivex.Observable

interface RepositoryContract {
    fun searchGithub(
        query: String,
        callback: RepositoryCallback
    )

    fun searchGithub(
        query: String
    ): Observable<SearchResponse>

    suspend fun searchGithubAsync(
        query: String
    ): SearchResponse

}

package com.example.gb_libs_lesson1.test.repository

import com.example.gb_libs_lesson1.test.model.SearchResponse
import com.example.gb_libs_lesson1.test.presenter.RepositoryContract
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class GitHubRepository(private val gitHubApi: GitHubApi) : RepositoryContract {

    override fun searchGithub(
        query: String,
        callback: RepositoryCallback
    ) {
        val call = gitHubApi.searchGithub(query)
        call?.enqueue(object : Callback<SearchResponse?> {

            override fun onResponse(
                call: Call<SearchResponse?>,
                response: Response<SearchResponse?>
            ) {
                callback.handleGitHubResponse(response)
            }

            override fun onFailure(
                call: Call<SearchResponse?>,
                t: Throwable
            ) {
                callback.handleGitHubError()
            }
        })
    }

    override fun searchGithub(query: String): Observable<SearchResponse> =
        gitHubApi.searchGithubRx(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override suspend fun searchGithubAsync(query: String): SearchResponse =
        gitHubApi.searchGithubAsync(query).await()

    interface GitHubRepositoryCallback {
        fun handleGitHubResponse(response: Response<SearchResponse?>?)
        fun handleGitHubError()
    }
}

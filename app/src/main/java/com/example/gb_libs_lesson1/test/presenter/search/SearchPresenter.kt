package com.example.gb_libs_lesson1.test.presenter.search

import com.example.gb_libs_lesson1.test.model.SearchResponse
import com.example.gb_libs_lesson1.test.presenter.PresenterContract
import com.example.gb_libs_lesson1.test.presenter.RepositoryContract
import com.example.gb_libs_lesson1.test.repository.GitHubRepository
import com.example.gb_libs_lesson1.test.repository.GitHubRepository.GitHubRepositoryCallback
import com.example.gb_libs_lesson1.test.repository.RepositoryCallback
import com.example.gb_libs_lesson1.test.util.SchedulerProvider
import com.example.gb_libs_lesson1.test.view.ViewContract
import com.example.gb_libs_lesson1.test.view.search.MainTestActivity
import com.example.gb_libs_lesson1.test.view.search.ViewSearchContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import retrofit2.Response

/**
 * В архитектуре MVP все запросы на получение данных адресуются в Репозиторий.
 * Запросы могут проходить через Interactor или UseCase, использовать источники
 * данных (DataSource), но суть от этого не меняется.
 * Непосредственно Презентер отвечает за управление потоками запросов и ответов,
 * выступая в роли регулировщика движения на перекрестке.
 */

internal class SearchPresenter internal constructor(
    private val viewContract: ViewSearchContract,
    private val repository: RepositoryContract,
    private val appSchedulerProvider: SchedulerProvider = SearchSchedulerProvider()
) : PresenterSearchContract, RepositoryCallback {

    var view: ViewContract? = null

    override fun searchGitHub(searchQuery: String) {
        //Dispose
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            repository.searchGithub(searchQuery)
                .subscribeOn(appSchedulerProvider.io())
                .observeOn(appSchedulerProvider.ui())
                .doOnSubscribe { viewContract.displayLoading(true) }
                .doOnTerminate { viewContract.displayLoading(false) }
                .subscribeWith(object : DisposableObserver<SearchResponse>() {

                    override fun onNext(searchResponse: SearchResponse) {
                        val searchResults = searchResponse.searchResults
                        val totalCount = searchResponse.totalCount
                        if (searchResults != null && totalCount != null) {
                            viewContract.displaySearchResults(
                                searchResults,
                                totalCount
                            )
                        } else {
                            viewContract.displayError("Search results or total count are null")
                        }
                    }

                    override fun onError(e: Throwable) {
                        viewContract.displayError(e.message ?: "Response is null or unsuccessful")
                    }

                    override fun onComplete() {}
                }
                )
        )
    }


    override fun onAttach(view: ViewContract) {
        if (this.view == null && this.view != view) {
            this.view = view
        }
    }

    override fun onDetach() {
        view = null
    }

    override fun handleGitHubResponse(response: Response<SearchResponse?>?) {
        viewContract.displayLoading(false)
        if (response != null && response.isSuccessful) {
            val searchResponse = response.body()
            val searchResults = searchResponse?.searchResults
            val totalCount = searchResponse?.totalCount
            if (searchResults != null && totalCount != null) {
                viewContract.displaySearchResults(
                    searchResults,
                    totalCount
                )
            } else {
                viewContract.displayError("Search results or total count are null")
            }
        } else {
            viewContract.displayError("Response is null or unsuccessful")
        }
    }

    override fun handleGitHubError() {
        viewContract.displayLoading(false)
        viewContract.displayError()
    }
}

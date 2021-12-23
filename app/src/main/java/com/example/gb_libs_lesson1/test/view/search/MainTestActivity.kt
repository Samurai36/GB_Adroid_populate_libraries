package com.example.gb_libs_lesson1.test.view.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gb_libs_lesson1.R
import com.example.gb_libs_lesson1.BuildConfig
import com.example.gb_libs_lesson1.test.model.SearchResult
import com.example.gb_libs_lesson1.test.presenter.RepositoryContract
import com.example.gb_libs_lesson1.test.presenter.search.PresenterSearchContract
import com.example.gb_libs_lesson1.test.presenter.search.SearchPresenter
import com.example.gb_libs_lesson1.test.repository.FakeGitHubRepository
import com.example.gb_libs_lesson1.test.repository.GitHubApi
import com.example.gb_libs_lesson1.test.repository.GitHubRepository
import com.example.gb_libs_lesson1.test.view.details.DetailsActivity
import kotlinx.android.synthetic.main.activitytest_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainTestActivity : AppCompatActivity(), ViewSearchContract {

    private val adapter = SearchResultAdapter()
    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }
    private var totalCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUI()
        viewModel.subscribeToLiveData().observe(this) { onStateChange(it) }
    }

    private fun onStateChange(screenState: ScreenState) {
        when (screenState) {
            is ScreenState.Working -> {
                val searchResponse = screenState.searchResponse
                val totalCount = searchResponse.totalCount
                progressBar.visibility = View.GONE
                with(totalCountTextView) {
                    visibility = View.VISIBLE
                    text =
                        String.format(
                            Locale.getDefault(),
                            getString(R.string.results_count),
                            totalCount
                        )
                }

                this.totalCount = totalCount!!
                adapter.updateResults(searchResponse.searchResults!!)
            }
            is ScreenState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Error -> {
                progressBar.visibility = View.GONE
                Toast.makeText(this, screenState.error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUI() {
        toDetailsActivityButton.setOnClickListener {
            startActivity(DetailsActivity.getIntent(this, totalCount))
        }
        setQueryListener()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun setQueryListener() {
        searchEditText.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = searchEditText.text.toString()
                if (query.isNotBlank()) {
                    viewModel.searchGitHub(query)
                    return@OnEditorActionListener true
                } else {
                    Toast.makeText(
                        this@MainTestActivity,
                        getString(R.string.enter_search_word),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@OnEditorActionListener false
                }
            }
            false
        })

        searchButtonTestMA.setOnClickListener {
            val query = searchEditText.text.toString()
            if (query.isNotBlank()) {
                viewModel.searchGitHub(query)
            }
        }
    }

    private fun createRepository(): RepositoryContract =
        GitHubRepository(createRetrofit().create(GitHubApi::class.java))

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun displaySearchResults(
        searchResults: List<SearchResult>,
        totalCount: Int
    ) {
        with(totalCountTextView) {
            visibility = View.VISIBLE
            text =
                String.format(Locale.getDefault(), getString(R.string.results_count), totalCount)
        }

        this.totalCount = totalCount
        adapter.updateResults(searchResults)
    }

    override fun displayError() {
        Toast.makeText(this, getString(R.string.undefined_error), Toast.LENGTH_SHORT).show()
    }

    override fun displayError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun displayLoading(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val BASE_URL = "https://api.github.com"
    }
}
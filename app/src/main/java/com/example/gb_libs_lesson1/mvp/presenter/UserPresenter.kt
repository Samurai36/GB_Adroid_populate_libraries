package com.example.gb_libs_lesson1.mvp.presenter

import com.example.gb_libs_lesson1.App
import com.example.gb_libs_lesson1.Screens.AndroidScreens
import com.example.gb_libs_lesson1.mvp.model.GithubRepositoriesRepo
import com.example.gb_libs_lesson1.mvp.model.IGithubRepositoriesRepo
import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubRepository
import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import com.example.gb_libs_lesson1.mvp.view.RepositoryItemView
import com.example.gb_libs_lesson1.mvp.view.ui.UserView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class UserPresenter(
    private val user: GithubUser
) : MvpPresenter<UserView>() {

    @Inject
    lateinit var repositoriesRepo: IGithubRepositoriesRepo

    @Inject
    @Named("schedulerMain")
    lateinit var schedulerUI: Scheduler

    @Inject
    lateinit var router: Router

    class RepositoriesListPresenter : IUserRepositoryListPresenter {

        val repositories = mutableListOf<GithubRepository>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null
        override fun getCount() = repositories.size

        override fun bindView(View: RepositoryItemView) {
            val repository = repositories[View.pos]
            View.showName(repository.name.orEmpty())
        }
    }

    val repositoriesListPresenter = RepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        repositoriesListPresenter.itemClickListener = { itemView ->
            val repository = repositoriesListPresenter.repositories[itemView.pos]
            router.navigateTo(AndroidScreens.RepositoryScreen(repository))
        }
    }

    private fun loadData() {
        repositoriesRepo.getRepositories(user)
            .observeOn(schedulerUI)
            .subscribe({ repositories ->
                repositoriesListPresenter.repositories.clear()
                repositoriesListPresenter.repositories.addAll(repositories)
                viewState.updateList()
            }, {
                it.printStackTrace()
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.releaseUserReposScope()
    }
}
package com.example.gb_libs_lesson1.mvp.presenter

import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubRepository
import com.example.gb_libs_lesson1.mvp.view.RepositoryView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepositoryPresenter(private val router: Router, private val githubRepository: GithubRepository) : MvpPresenter<RepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setName(githubRepository.name.orEmpty())
        viewState.setFullName(githubRepository.fullName.orEmpty())
        viewState.setDescription(githubRepository.description.orEmpty())
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}

package com.example.gb_libs_lesson1.mvp.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gb_libs_lesson1.App
import com.example.gb_libs_lesson1.databinding.FragmentUserRepositoriesBinding
import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubRepository
import com.example.gb_libs_lesson1.mvp.presenter.RepositoryPresenter
import com.example.gb_libs_lesson1.mvp.view.BackButtonListener
import com.example.gb_libs_lesson1.mvp.view.RepositoryView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserRepositoriesFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    private var vb: FragmentUserRepositoriesBinding? = null

    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: GithubRepository) = UserRepositoriesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

    val presenter: RepositoryPresenter by moxyPresenter {
        val repository =
            arguments?.getParcelable<GithubRepository>(REPOSITORY_ARG) as GithubRepository
        RepositoryPresenter(App.instance.router, repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserRepositoriesBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun init() {}

    override fun setName(name: String) {
        vb?.userRepoName?.text = name
    }

    override fun setFullName(fullName: String) {
        vb?.userRepoFullName?.text = fullName
    }

    override fun setDescription(description: String) {
        vb?.userRepoDescription?.text = description
    }


    override fun backPressed() = presenter.backClick()
}
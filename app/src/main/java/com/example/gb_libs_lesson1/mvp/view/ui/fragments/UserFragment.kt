package com.example.gb_libs_lesson1.mvp.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_libs_lesson1.App
import com.example.gb_libs_lesson1.FragmentInitializer
import com.example.gb_libs_lesson1.databinding.FragmentUserBinding
import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import com.example.gb_libs_lesson1.mvp.presenter.UserPresenter
import com.example.gb_libs_lesson1.mvp.view.BackButtonListener
import com.example.gb_libs_lesson1.mvp.view.ui.UserView
import com.example.gb_libs_lesson1.mvp.view.ui.adapters.UserRepositoriesRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var vb: FragmentUserBinding? = null

    private val user by initParams<GithubUser>()

    private val adapter by lazy { UserRepositoriesRVAdapter(presenter.repositoriesListPresenter) }

    companion object : FragmentInitializer<GithubUser>

    private val presenter: UserPresenter by moxyPresenter {
        App.instance.initUserRepositorySubcomponent()
        UserPresenter(user
        ).apply {
            App.instance.userRepositorySubcomponent?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun init() {
        vb?.rvUserRepos?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvUserRepos?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backClick()

}
package com.example.gb_libs_lesson1.mvp.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_libs_lesson1.mvp.view.BackButtonListener
import com.example.gb_libs_lesson1.databinding.FragmentUsersBinding
import com.example.gb_libs_lesson1.mvp.model.GithubUsersRepo
import com.example.gb_libs_lesson1.mvp.presenter.UsersPresenter
import com.example.gb_libs_lesson1.App
import com.example.gb_libs_lesson1.mvp.model.GithubDatabase
import com.example.gb_libs_lesson1.mvp.model.GithubUserCache
import com.example.gb_libs_lesson1.mvp.model.network.AndroidNetworkStatus
import com.example.gb_libs_lesson1.mvp.view.ui.adapters.UsersRVAdapter
import com.example.gb_libs_lesson1.mvp.view.ui.UsersView
import com.example.gb_libs_lesson1.mvp.view.ui.images.GlideImageLoader
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var vb: FragmentUsersBinding? = null

    private val presenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepo(
                AndroidNetworkStatus(requireContext()),
                GithubUserCache(GithubDatabase.getInstance())
            ),
            App.instance.router,
            Schedulers.io()
        )
    }

    private val adapter by lazy { UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUsersBinding.inflate(
            inflater,
            container, false
        ).also {
            vb = it
        }.root
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}
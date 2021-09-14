package com.example.gb_libs_lesson1.mvp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gb_libs_lesson1.App
import com.example.gb_libs_lesson1.databinding.FragmentUserBinding
import com.example.gb_libs_lesson1.mvp.model.GithubUser
import com.example.gb_libs_lesson1.mvp.presenter.UserPresenter
import com.example.gb_libs_lesson1.mvp.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var vb: FragmentUserBinding? = null

    companion object {

        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    val presenter: UserPresenter by moxyPresenter {
        val user: GithubUser = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(user, App.instance.router)
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


    override fun backPressed() = presenter.backClick()

    override fun setLogin(text: String) {
        vb?.userLogin?.text = text
    }

}
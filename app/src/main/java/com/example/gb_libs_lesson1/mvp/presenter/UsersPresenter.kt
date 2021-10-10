package com.example.gb_libs_lesson1.mvp.presenter

import com.example.gb_libs_lesson1.App
import com.example.gb_libs_lesson1.Screens.AndroidScreens
import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import com.example.gb_libs_lesson1.mvp.model.GithubUsersRepo
import com.example.gb_libs_lesson1.mvp.model.IGithubUsersRepo
import com.example.gb_libs_lesson1.mvp.view.UserItemView
import com.example.gb_libs_lesson1.mvp.view.ui.UsersView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class UsersPresenter : MvpPresenter<UsersView>() {

    @Inject
    lateinit var usersRepo: IGithubUsersRepo

    @Inject
    @Named("schedulerIo")
    lateinit var schedulerUI: Scheduler

    @Inject
    lateinit var router: Router

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount(): Int = users.size

        override fun bindView(View: UserItemView) {
            val user = users[View.pos]
            View.showLogin(user.login.orEmpty())
            View.showAvatar(user.avatarURL.orEmpty())
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user: GithubUser = usersListPresenter.users[itemView.pos]
            router.navigateTo(AndroidScreens.UserScreen(user))
        }
    }

    private fun loadData() {
        usersRepo.getUsers().subscribeOn(schedulerUI)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(it)
                viewState.updateList()
            }, {
                it.printStackTrace()
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.releaseUsersScope()
    }
}
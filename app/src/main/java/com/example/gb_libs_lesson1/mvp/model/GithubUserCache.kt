package com.example.gb_libs_lesson1.mvp.model

import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import com.example.gb_libs_lesson1.mvp.model.dataclasses.RoomGithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Named

class GithubUserCache @Inject constructor(
    val db: GithubDatabase,
    @Named("schedulerIo")
    private val schedulerUI: Scheduler
) : IGithubUserCache {
    override fun getUsers(): Single<List<GithubUser>> {
        return Single.fromCallable {
            db.userDao.getAll().map {
                GithubUser(it.id, it.login, it.avatarURL, it.reposURL)
            }
        }
    }

    override fun putUsers(users: List<GithubUser>): Completable {
        return Completable.fromAction {
            val roomUsers = users.map { user ->
                RoomGithubUser(
                    user.id.orEmpty(),
                    user.login.orEmpty(),
                    user.avatarURL.orEmpty(),
                    user.reposURL.orEmpty()
                )
            }
            db.userDao.insert(roomUsers)
        }.subscribeOn(schedulerUI)
    }
}
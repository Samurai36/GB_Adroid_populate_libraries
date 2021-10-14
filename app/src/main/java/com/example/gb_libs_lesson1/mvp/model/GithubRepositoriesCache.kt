package com.example.gb_libs_lesson1.mvp.model

import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubRepository
import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import com.example.gb_libs_lesson1.mvp.model.dataclasses.RoomGithubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class GithubRepositoriesCache @Inject constructor(
    private val db: GithubDatabase
) : IGithubRepositoriesCache {
    override fun getUserRepos(user: GithubUser): Single<List<GithubRepository>> =
        Single.fromCallable {
            val roomUser = db.userDao.findByLogin(user.login.orEmpty())
                ?: error("No find user in cache")
            return@fromCallable db.repositoryDao.getByUserId(roomUser.id).map {
                GithubRepository(
                    id = it.id,
                    name = it.name,
                    fullName = it.fullName,
                    description = it.description
                )
            }
        }.subscribeOn(Schedulers.io())

    override fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>): Completable {
        return Completable.fromAction {
            val roomUser =
                db.userDao.findByLogin(user.login.orEmpty()) ?: error("don't found user in cache")
            val roomRepos = repositories.map {
                RoomGithubRepository(
                    it.id,
                    it.name.orEmpty(),
                    it.fullName.orEmpty(),
                    it.description.orEmpty(),
                    roomUser.id
                )
            }
            db.repositoryDao.insert(roomRepos)
        }.subscribeOn(Schedulers.io())
    }
}
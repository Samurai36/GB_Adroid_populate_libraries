package com.example.gb_libs_lesson1.mvp.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gb_libs_lesson1.mvp.model.dataclasses.RoomGithubRepository
import com.example.gb_libs_lesson1.mvp.model.dataclasses.RoomGithubUser


@Database(
    entities = [RoomGithubUser::class, RoomGithubRepository::class],
    version = 1
)

abstract class GithubDatabase : RoomDatabase() {
    abstract val userDao: IUserDao
    abstract val repositoryDao: IRepositoryDao
}
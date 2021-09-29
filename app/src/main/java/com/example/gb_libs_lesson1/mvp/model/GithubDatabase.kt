package com.example.gb_libs_lesson1.mvp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        private const val DB_NAME = "databaseGit.db"
        private var instance: GithubDatabase? = null

        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created, please call create(context)")

        fun create(context: Context) {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, GithubDatabase::class.java, DB_NAME).build()
            }

        }
    }
}
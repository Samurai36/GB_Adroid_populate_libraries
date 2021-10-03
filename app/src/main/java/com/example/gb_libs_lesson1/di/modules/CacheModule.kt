package com.example.gb_libs_lesson1.di.modules

import android.content.Context
import androidx.room.Room
import com.example.gb_libs_lesson1.mvp.model.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Suppress
    fun getUsersCache(db: GithubDatabase): IGithubUserCache {
        return GithubUserCache(db)
    }

    @Provides
    @Suppress
    fun getUserReposCache(db: GithubDatabase): IGithubRepositoriesCache {
        return GithubRepositoriesCache(db)
    }

    @Provides
    @Singleton
    fun db(context: Context): GithubDatabase {
        return Room.databaseBuilder(context, GithubDatabase::class.java, DB_NAME)
            .build()
    }
    companion object {
        private const val DB_NAME = "databaseGit.db"
    }
}

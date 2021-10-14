package com.example.gb_libs_lesson1.di.modules

import android.content.Context
import com.example.gb_libs_lesson1.mvp.model.network.AndroidNetworkStatus
import com.example.gb_libs_lesson1.mvp.model.network.INetworkStatus
import com.example.gb_libs_lesson1.mvp.model.remote.ApiHolder
import com.example.gb_libs_lesson1.mvp.model.remote.GithubUsersService
import com.example.gb_libs_lesson1.mvp.model.remote.IApiHolder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
interface ApiModule {

    @Binds
    fun apiHolder(impl: ApiHolder): IApiHolder

    companion object {
        @Provides
        @Singleton
        @Named("baseUrl")
        fun baseUrl(): String = "https://api.github.com"

        @Provides
        @Singleton
        fun gson(): Gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        @Provides
        @Singleton
        fun githubUsersService(
            gson: Gson,
            @Named("baseUrl") baseUrl: String
        ): GithubUsersService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(GithubUsersService::class.java)
        }

        @Provides
        @Singleton
        fun networkStatus(context: Context): INetworkStatus = AndroidNetworkStatus(context)

    }
}
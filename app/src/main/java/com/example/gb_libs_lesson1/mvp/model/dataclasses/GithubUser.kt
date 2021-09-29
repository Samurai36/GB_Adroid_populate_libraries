package com.example.gb_libs_lesson1.mvp.model.dataclasses

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    @Expose
    val id: String?,

    @Expose
    val login: String?,

    @Expose
    @SerializedName("avatar_url")
    val avatarURL: String? = null,


    @Expose
    @SerializedName("repos_url")
    val reposURL: String? = null
) : Parcelable
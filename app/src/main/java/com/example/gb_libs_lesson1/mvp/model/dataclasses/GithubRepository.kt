package com.example.gb_libs_lesson1.mvp.model.dataclasses

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepository(

    @Expose
    val id: String,

    @Expose
    val name: String? = null,

    @Expose
    @SerializedName("full_name")
    val fullName: String? = null,

    @Expose
    val description: String? = null
) : Parcelable {

}
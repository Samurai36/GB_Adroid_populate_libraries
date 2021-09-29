package com.example.gb_libs_lesson1.mvp.model.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGithubUser(

    @PrimaryKey
    val id: String,

    val login: String,
    val avatarURL: String,
    val reposURL: String
)
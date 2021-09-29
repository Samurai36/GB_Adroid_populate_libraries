package com.example.gb_libs_lesson1.mvp.model.dataclasses

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
        )]
)
data class RoomGithubRepository(

    @PrimaryKey
    val id: String,

    val name: String,
    val fullName: String,
    val description: String,
    val userId: String
)

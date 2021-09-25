package com.example.gb_libs_lesson1.mvp.view.ui.images

interface IImageLoader<T> {

    fun loadTo(url: String, target: T)
}
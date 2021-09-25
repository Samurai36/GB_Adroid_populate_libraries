package com.example.gb_libs_lesson1.mvp.view.ui.images

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader: IImageLoader<ImageView> {
    override fun loadTo(url: String, target: ImageView) {
        Glide.with(target.context)
            .load(url)
            .into(target)
    }
}
package com.example.gb_libs_lesson1.mvp.view.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_libs_lesson1.databinding.ItemUserBinding
import com.example.gb_libs_lesson1.mvp.presenter.IUserListPresenter
import com.example.gb_libs_lesson1.mvp.view.UserItemView
import com.example.gb_libs_lesson1.mvp.view.ui.images.GlideImageLoader
import javax.inject.Inject

class UsersRVAdapter @Inject constructor(
    private val presenter: IUserListPresenter,
    private val imageLoader: GlideImageLoader
) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {

        override var pos: Int = -1

        override fun showLogin(login: String) {
            vb.tvLogin.text = login
        }

        override fun showAvatar(url: String) {
            imageLoader.loadTo(url, vb.ivAvatar)
        }
    }
}
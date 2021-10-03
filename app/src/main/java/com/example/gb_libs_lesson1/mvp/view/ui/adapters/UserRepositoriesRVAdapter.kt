package com.example.gb_libs_lesson1.mvp.view.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_libs_lesson1.databinding.ItemUserReposBinding
import com.example.gb_libs_lesson1.mvp.presenter.IUserRepositoryListPresenter
import com.example.gb_libs_lesson1.mvp.view.RepositoryItemView
import javax.inject.Inject

class UserRepositoriesRVAdapter  @Inject constructor(val presenter: IUserRepositoryListPresenter) :
    RecyclerView.Adapter<UserRepositoriesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserReposBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    inner class ViewHolder(private val vb: ItemUserReposBinding) :
        RecyclerView.ViewHolder(vb.root), RepositoryItemView {

        override var pos = -1

        override fun showName(name: String) {

            vb.tvReposName.text = name
        }

    }
}
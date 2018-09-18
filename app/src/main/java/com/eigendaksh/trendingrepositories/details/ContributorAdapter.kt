package com.eigendaksh.trendingrepositories.details

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.eigendaksh.trendingrepositories.R
import com.eigendaksh.trendingrepositories.model.Contributor

internal class ContributorAdapter : RecyclerView.Adapter<ContributorAdapter.ContributorViewHolder>() {

    private val data: MutableList<Contributor> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.contributor_list_item, parent, false)
        return ContributorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return data.get(position).id
    }

    fun setData(contributors: List<Contributor>?) {
        if (contributors != null) {
            val diffResult = DiffUtil.calculateDiff(ContributorDiffCallback(data, contributors))
            data.clear()
            data.addAll(contributors)
            diffResult.dispatchUpdatesTo(this)
        } else {
            data.clear()
            notifyDataSetChanged()
        }
    }

    class ContributorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tvUserName)
        lateinit var usernameText: TextView
        @BindView(R.id.ivAvatar)
        lateinit var avatarImageView: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(contributor: Contributor) {
            usernameText.setText(contributor.login)
            Glide.with(avatarImageView.getContext())
                    .load(contributor.avatarUrl)
                    .into(avatarImageView)
        }
    }
}
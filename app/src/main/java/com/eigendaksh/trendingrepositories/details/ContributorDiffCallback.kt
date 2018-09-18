package com.eigendaksh.trendingrepositories.details

import android.support.v7.util.DiffUtil
import com.eigendaksh.trendingrepositories.model.Contributor

class ContributorDiffCallback(private val oldList: List<Contributor>, private val newList: List<Contributor>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList.get(newItemPosition).id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
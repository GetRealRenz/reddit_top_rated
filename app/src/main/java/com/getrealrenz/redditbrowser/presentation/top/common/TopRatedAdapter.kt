package com.getrealrenz.redditbrowser.presentation.top.common

import android.support.v7.util.DiffUtil
import com.getrealrenz.redditbrowser.R
import com.getrealrenz.redditbrowser.data.entity.PostData
import com.getrealrenz.redditbrowser.databinding.ListItemTopRatedBinding
import com.getrealrenz.redditbrowser.load
import com.getrealrenz.redditbrowser.presentation.base.BaseRecyclerAdapter
import com.getrealrenz.redditbrowser.presentation.base.BaseViewHolder

class TopRatedAdapter constructor(var posts: MutableList<PostData?> = mutableListOf()) : BaseRecyclerAdapter<ListItemTopRatedBinding>() {
    override fun bindItem(holder: BaseViewHolder<ListItemTopRatedBinding>, position: Int) {
        holder.binding.apply {
            model = posts[position]
            ivTopRatedIco.load(model?.thumbnail)
        }
    }

    override fun getLayoutId(position: Int): Int = R.layout.list_item_top_rated

    override fun getItemCount() = posts.size

    fun setData(list: MutableList<PostData?>) {
        val oldList = this.posts
        this.posts.addAll(list)

        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] === posts[newItemPosition]
            }

            override fun getOldListSize(): Int = oldList.size

            override fun getNewListSize(): Int = posts.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == posts[newItemPosition]
            }
        }).dispatchUpdatesTo(this)
    }
}
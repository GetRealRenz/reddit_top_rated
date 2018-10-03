package com.getrealrenz.redditbrowser.presentation.top.common

import android.support.v7.util.DiffUtil
import com.getrealrenz.redditbrowser.R
import com.getrealrenz.redditbrowser.data.entity.PostData
import com.getrealrenz.redditbrowser.databinding.ListItemTopRatedBinding
import com.getrealrenz.redditbrowser.load
import com.getrealrenz.redditbrowser.presentation.base.BaseRecyclerAdapter
import com.getrealrenz.redditbrowser.presentation.base.BaseViewHolder
import com.getrealrenz.redditbrowser.presentation.top.TopRatedContract

class TopRatedAdapter constructor(var posts: MutableList<PostData?> = mutableListOf(),
                                  var eventListener: TopRatedContract.Presenter) : BaseRecyclerAdapter<ListItemTopRatedBinding>() {
    override fun bindItem(holder: BaseViewHolder<ListItemTopRatedBinding>, position: Int) {
        holder.binding.apply {
            model = posts[position]
            event = eventListener
            ivTopRatedIco.load(model?.thumbnail)
            executePendingBindings()
        }
    }

    override fun getLayoutId(position: Int): Int = R.layout.list_item_top_rated

    override fun getItemCount() = posts.size

    fun setData(list: MutableList<PostData?>) {
        val oldList = this.posts.size
        this.posts.addAll(list)
        notifyItemRangeInserted(oldList+1,posts.size)
    }
}
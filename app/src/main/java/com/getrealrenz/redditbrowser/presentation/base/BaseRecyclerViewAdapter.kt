package com.getrealrenz.redditbrowser.presentation.base

import android.databinding.DataBindingUtil
import android.databinding.OnRebindCallback
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseRecyclerAdapter<T : ViewDataBinding> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    private var recyclerView: RecyclerView? = null

    private val mPreBindCallback : OnRebindCallback<T> = object : OnRebindCallback<T>() {
        override fun onPreBind(binding: T): Boolean {
            if (recyclerView == null || recyclerView?.isComputingLayout == true) return true
            if(binding.root.layoutParams is RecyclerView.LayoutParams){
                val adapterPosition = recyclerView?.getChildAdapterPosition(binding.root) ?: RecyclerView.NO_POSITION
                if(adapterPosition == RecyclerView.NO_POSITION) return true

                notifyItemChanged(adapterPosition)
            }

            return false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val holder = BaseViewHolder.create<T>(parent, viewType)
        holder.binding.addOnRebindCallback(mPreBindCallback)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        bindItem(holder, position)
        holder.binding.executePendingBindings()
    }

    override fun getItemViewType(position: Int): Int = getLayoutId(position)

    abstract fun bindItem(holder: BaseViewHolder<T>, position: Int)

    @LayoutRes
    abstract fun getLayoutId(position: Int): Int

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

}


class BaseViewHolder<out T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun <T : ViewDataBinding> create(parent: ViewGroup, @LayoutRes layoutId: Int) =
                BaseViewHolder<T>(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false))
    }
}
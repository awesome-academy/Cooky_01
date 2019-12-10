package com.example.cooky.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseRecyclerViewAdapter<Item, ViewBinding : ViewDataBinding>(
    callBack: DiffUtil.ItemCallback<Item>
) : ListAdapter<Item, BaseViewHolder<ViewBinding>>(callBack) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate<ViewBinding>(
                LayoutInflater.from(parent.context),
                layoutResource,
                parent,
                false
            ).apply { setEvent(this) }
        )

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding>, position: Int) {
        bindViewHolder(holder.binding, getItem(position))
        holder.binding.executePendingBindings()
    }

    @get:LayoutRes
    protected abstract val layoutResource: Int

    abstract fun bindViewHolder(binding: ViewBinding, item: Item)

    protected open fun setEvent(binding: ViewBinding) {}
}

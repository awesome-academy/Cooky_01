package com.example.cooky.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.cooky.R
import com.example.cooky.base.BaseRecyclerViewAdapter
import com.example.cooky.data.local.model.search.IntroRecipe
import com.example.cooky.databinding.ItemIntroHorizontalBinding

class IntroAdapterHorizontal(
    private var onItemClick: (IntroRecipe) -> Unit
) : BaseRecyclerViewAdapter<IntroRecipe, ItemIntroHorizontalBinding>(DiffUtilCallback()) {

    override val layoutResource: Int = R.layout.item_intro_horizontal

    override fun setEvent(binding: ItemIntroHorizontalBinding) {
        binding.itemRecipe.setOnClickListener {
            binding.item?.let { onItemClick(it) }
        }
    }

    override fun bindViewHolder(binding: ItemIntroHorizontalBinding, item: IntroRecipe) {
        binding.item = item
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<IntroRecipe>() {
        override fun areItemsTheSame(oldItem: IntroRecipe, newItem: IntroRecipe): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: IntroRecipe, newItem: IntroRecipe): Boolean =
            oldItem == newItem
    }
}

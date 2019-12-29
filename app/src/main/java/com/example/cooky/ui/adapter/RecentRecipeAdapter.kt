package com.example.cooky.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.cooky.R
import com.example.cooky.base.BaseRecyclerViewAdapter
import com.example.cooky.data.local.model.search.IntroRecipe
import com.example.cooky.databinding.ItemRecipeRecentlyBinding

class RecentRecipeAdapter(
    private val onItemClick: (IntroRecipe) -> Unit
) : BaseRecyclerViewAdapter<IntroRecipe, ItemRecipeRecentlyBinding>(DiffUtilCallback()) {

    override val layoutResource: Int = R.layout.item_recipe_recently

    override fun bindViewHolder(binding: ItemRecipeRecentlyBinding, item: IntroRecipe) {
        binding.item = item
    }

    override fun setEvent(binding: ItemRecipeRecentlyBinding) {
        binding.itemRecipe.setOnClickListener {
            binding.item?.let(onItemClick)
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<IntroRecipe>() {
        override fun areItemsTheSame(oldItem: IntroRecipe, newItem: IntroRecipe) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: IntroRecipe, newItem: IntroRecipe) =
            oldItem == newItem
    }
}

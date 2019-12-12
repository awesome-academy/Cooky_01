package com.example.cooky.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.cooky.R
import com.example.cooky.base.BaseRecyclerViewAdapter
import com.example.cooky.data.local.model.search.IntroRecipe
import com.example.cooky.databinding.ItemIntroHorizontalBinding
import com.example.cooky.databinding.ItemIntroVeritcalBinding

class IntroAdapterVertical(
    private var onItemClick: (IntroRecipe) -> Unit
) : BaseRecyclerViewAdapter<IntroRecipe, ItemIntroVeritcalBinding>(DiffUtilCallback()) {

    override val layoutResource: Int = R.layout.item_intro_veritcal

    override fun setEvent(binding: ItemIntroVeritcalBinding) {
        binding.itemRecipe.setOnClickListener {
            binding.item?.let(onItemClick)
        }
    }

    override fun bindViewHolder(binding: ItemIntroVeritcalBinding, item: IntroRecipe) {
        binding.item = item
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<IntroRecipe>() {
        override fun areItemsTheSame(oldItem: IntroRecipe, newItem: IntroRecipe) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: IntroRecipe, newItem: IntroRecipe) =
            oldItem == newItem
    }
}

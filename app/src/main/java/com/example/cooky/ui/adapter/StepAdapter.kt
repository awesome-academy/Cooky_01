package com.example.cooky.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.cooky.R
import com.example.cooky.base.BaseRecyclerViewAdapter
import com.example.cooky.data.local.model.recipe.Step
import com.example.cooky.databinding.ItemStepBinding

class StepAdapter : BaseRecyclerViewAdapter<Step, ItemStepBinding>(DiffUtilCallback()) {

    override val layoutResource: Int = R.layout.item_step

    override fun bindViewHolder(binding: ItemStepBinding, item: Step) {
        binding.item = item
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Step>() {
        override fun areItemsTheSame(oldItem: Step, newItem: Step) =
            oldItem.number == newItem.number

        override fun areContentsTheSame(oldItem: Step, newItem: Step) =
            oldItem == newItem
    }
}

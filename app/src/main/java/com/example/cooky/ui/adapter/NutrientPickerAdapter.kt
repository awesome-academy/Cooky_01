package com.example.cooky.ui.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.cooky.R
import com.example.cooky.base.BaseRecyclerViewAdapter
import com.example.cooky.data.remote.api.CALORIES
import com.example.cooky.databinding.ItemNutrientPickerBinding
import com.example.cooky.ui.search.bynutrients.NutrientPicker

class NutrientPickerAdapter(
    private val onRangePickerChange: (String, Int, Int) -> Unit
) : BaseRecyclerViewAdapter<NutrientPicker, ItemNutrientPickerBinding>(DiffUtilCallback()) {

    override val layoutResource = R.layout.item_nutrient_picker

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(binding: ItemNutrientPickerBinding, item: NutrientPicker) {
        binding.apply {
            this.item = item
            if (item.title == CALORIES) {
                textTitle.text = "${item.title}$TEXT_COLON"
            }
            rangePicker.apply {
                max = item.max
                min = item.min
                getThumb(0).value = item.start
                getThumb(1).value = item.end
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setEvent(binding: ItemNutrientPickerBinding) {
        binding.apply {
            rangePicker.setOnThumbValueChangeListener { multiSlider, thumb, thumbIndex, value ->
                item?.let { onRangePickerChange(it.title, thumbIndex, value) }
                if (thumbIndex == 0) {
                    textStart.text = "$value$TEXT_DASH"
                } else {
                    textEnd.text = "$value"
                }
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<NutrientPicker>() {
        override fun areItemsTheSame(oldItem: NutrientPicker, newItem: NutrientPicker) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: NutrientPicker, newItem: NutrientPicker) =
            oldItem == newItem

    }

    companion object {
        const val TEXT_DASH = " - "
        const val TEXT_COLON = " : "
    }
}

package com.example.cooky.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun BottomSheetBehavior<ConstraintLayout>.setVisible(isVisible: Boolean) {
    this.state = if (isVisible) {
        BottomSheetBehavior.STATE_EXPANDED
    } else {
        BottomSheetBehavior.STATE_HIDDEN
    }
}

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0)
}

fun View.onShow(isShow: Boolean) {
    this.visibility = if (isShow) View.VISIBLE else View.GONE
}

fun integerListToString(list: List<Int>) =
    Gson().toJson(list)

fun stringToListInteger(string: String) : MutableList<Int>{
    val listType = object : TypeToken<List<Int>>() {}.type
    val result = Gson().fromJson<MutableList<Int>>(string, listType)
    return result
}

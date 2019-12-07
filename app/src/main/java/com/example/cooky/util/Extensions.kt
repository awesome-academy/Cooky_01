package com.example.cooky.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

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

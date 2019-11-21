package com.example.cooky.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    protected abstract val layoutResource: Int
    protected abstract val viewModel: VB
    private lateinit var viewDataBinding: VB

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResource)
        setBindingVariables()
    }

    open fun setBindingVariables() {
    }
}

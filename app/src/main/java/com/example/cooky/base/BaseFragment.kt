package com.example.cooky.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel>() : Fragment() {
    protected abstract val layoutResource: Int
    protected abstract val viewModel: VM
    private lateinit var dataBinding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }
        setBindingVariables()
    }

    open fun setBindingVariables() {
    }
}

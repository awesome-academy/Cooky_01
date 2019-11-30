package com.example.cooky.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.cooky.util.showToast

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
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
        initView()
        initData()
        observeViewModel()
    }

    protected abstract fun initView()

    protected open fun initData() {
    }

    protected abstract fun setBindingVariables()

    protected open fun observeViewModel() = with(viewModel) {
        isLoading.observe(viewLifecycleOwner, Observer { handleLoading(it) })
        message.observe(viewLifecycleOwner, Observer { handleGetMessage(it) })
    }

    private fun handleGetMessage(message: String) {
        context?.showToast(message)
        hideLoading()
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) showLoading() else hideLoading()
    }

    open fun showLoading() {
    }

    open fun hideLoading() {
    }
}

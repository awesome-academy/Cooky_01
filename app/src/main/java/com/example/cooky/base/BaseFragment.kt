package com.example.cooky.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.cooky.util.showToast

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel>() : Fragment() {
    protected abstract val layoutResource: Int
    protected abstract val viewModel: VM
    protected lateinit var dataBinding: VB
    protected var isFirstLoaded = false

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
        savedInstanceState?.let { isFirstLoaded = it.getBoolean(FIRST_LOADED) }
        setBindingVariables()
        initView()
        initData()
        if (!isFirstLoaded) {
            observeViewModel()
            isFirstLoaded = true
        }

    }

    protected abstract fun initView()

    protected open fun initData() {
    }

    protected abstract fun setBindingVariables()

    protected open fun observeViewModel() = with(viewModel) {
        isLoading.observe(viewLifecycleOwner, Observer(::handleLoading))
        message.observe(viewLifecycleOwner, Observer(::handleGetMessage))
    }

    private fun handleGetMessage(message: String) {
        context?.showToast(message)
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) showLoading() else hideLoading()
    }

    open fun showLoading() {
    }

    open fun hideLoading() {
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FIRST_LOADED, isFirstLoaded)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { isFirstLoaded = it.getBoolean(FIRST_LOADED) }
    }

    companion object {
        const val FIRST_LOADED = "is first loaded"
    }
}

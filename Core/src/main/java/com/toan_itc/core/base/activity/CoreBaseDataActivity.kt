package com.toan_itc.core.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.toan_itc.core.base.BaseViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class CoreBaseDataActivity<VM : BaseViewModel, DB : ViewDataBinding> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel: VM
    protected lateinit var binding: DB
    abstract fun getViewModel(): Class<VM>
    abstract fun inflateBindingLayout(inflater: LayoutInflater): DB?

    protected abstract fun initViews()
    protected abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
        binding = setupBinding(layoutInflater)!!
        setContentView(binding.root)
        initViews()
        initData()
    }

    private fun setupBinding(inflater: LayoutInflater): DB? {
        val binding = inflateBindingLayout(inflater)
        binding?.setVariable(BR.view, this)
        binding?.setVariable(BR.viewModel, viewModel)
        return binding
    }

}
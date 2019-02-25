package com.toan_itc.core.base.fragment

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.toan_itc.core.architecture.AppExecutors
import com.toan_itc.core.base.BaseViewModel
import com.toan_itc.core.base.di.Injectable
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class CoreBaseDataFragment<VM : BaseViewModel> : DaggerFragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appExecutors: AppExecutors
    lateinit var viewModel: VM
    abstract fun getViewModel(): Class<VM>
    @LayoutRes
    abstract fun setLayoutResourceID(): Int
    abstract fun initData()
    abstract fun initView()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(getViewModel())
        initView()
        initData()
    }
}
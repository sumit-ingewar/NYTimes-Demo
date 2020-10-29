package com.sumit.test.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sumit.test.R
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<VDB : ViewDataBinding, BVM : BaseViewModel<*>> :
    DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var injectedViewModel: BVM

    lateinit var viewDataBinding: VDB

    abstract val viewModel: Class<BVM>

    abstract fun getBindingVariable(): Int

    @get:LayoutRes
    abstract val layoutId: Int

    private val disposableDelegate = lazy { CompositeDisposable() }

    private val compositeDisposable by disposableDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()

        initUserInterface()

    }

    protected abstract fun initUserInterface()

    protected open fun getBaseFragmeLayoutId() =
        R.id.activity_base_fragment_container


    private fun performDataBinding() {

        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        injectedViewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModel)
        viewDataBinding.setVariable(getBindingVariable(), injectedViewModel)
        viewDataBinding.executePendingBindings()

    }

    override fun onDestroy() {
        if(disposableDelegate.isInitialized()){
            compositeDisposable.dispose()
        }
        super.onDestroy()
    }
}
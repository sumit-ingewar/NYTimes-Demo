package com.sumit.test.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<VB : ViewBinding> :
    AppCompatActivity() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val viewBinding: VB
        get() = _binding as VB

    private val disposableDelegate = lazy { CompositeDisposable() }
    private val compositeDisposable by disposableDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        initUserInterface()
    }

    protected abstract fun initUserInterface()

    override fun onDestroy() {
        super.onDestroy()
        if (disposableDelegate.isInitialized()) {
            compositeDisposable.dispose()
        }
        _binding = null
    }
}
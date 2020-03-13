package com.sumit.test.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sumit.core.DemoLogger
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_list.view.*
import javax.inject.Inject

abstract class BaseFragment<VDB : ViewDataBinding,
        BVM : BaseViewModel<*>> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var injectedViewModel: BVM

     var viewDataBinding: VDB? = null

    abstract val viewModel: Class<BVM>

    abstract fun getBindingVariable(): Int

    private val disposableDelegate = lazy { CompositeDisposable() }

    protected val compositeDisposable by disposableDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectedViewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )
        return viewDataBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding!!.setVariable(getBindingVariable(), injectedViewModel)
        viewDataBinding!!.lifecycleOwner = viewLifecycleOwner
        initUserInterface(view)
    }

    override fun onDestroyView() {
        viewDataBinding = null
        super.onDestroyView()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initUserInterface(view: View?)
}
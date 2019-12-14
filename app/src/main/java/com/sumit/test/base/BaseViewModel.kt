package com.sumit.test.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N> : ViewModel() {

    lateinit var navigator: WeakReference<N>

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        with(compositeDisposable) {
            if (isDisposed.not()) {
                dispose()
                compositeDisposable = CompositeDisposable()
            }
        }
        super.onCleared()
    }

    fun getNavigator(): N? {
        return navigator.get()
    }

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

    fun addDisposable(disposable: Disposable?) {
        disposable?.let {
            if (it.isDisposed.not()) {
                compositeDisposable.add(disposable)
            }
        }
    }
}
package com.sumit.test.viewmodelprovider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sumit.core.DemoLogger
import com.sumit.core.di.scopes.PerApplication
import com.sumit.core.extensions.safeGet
import javax.inject.Inject
import javax.inject.Provider

@PerApplication
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("Unknown ViewModel: '$modelClass'")
        }

        try {
            return creator.get() as T
        } catch (e: Exception) {
            DemoLogger.e(ViewModelFactory::class.java.simpleName, e.message.safeGet())
            throw  RuntimeException(e)
        }
    }
}

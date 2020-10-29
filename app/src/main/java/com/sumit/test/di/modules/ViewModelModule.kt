package com.sumit.test.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sumit.test.di.key.ViewModelKey
import com.sumit.test.ui.activity.main.MainActivityViewModel
import com.sumit.test.ui.fragment.ListFragmentViewModel
import com.sumit.test.ui.fragment.details.DetailFragmentViewModel
import com.sumit.test.viewmodelprovider.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListFragmentViewModel::class)
    fun bindListFragmentViewModel(listFragmentViewModel: ListFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailFragmentViewModel::class)
    fun bindListDetailsViewModel(detailFragmentViewModel: DetailFragmentViewModel): ViewModel

}
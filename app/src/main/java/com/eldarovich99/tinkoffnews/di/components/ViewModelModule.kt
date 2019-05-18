package com.eldarovich99.tinkoffnews.di.components

import android.arch.lifecycle.ViewModel
import com.eldarovich99.tinkoffnews.presentation.newsfeed.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{
    @IntoMap
    @Binds
    @ViewModelKey(NewsViewModel::class)
    abstract fun newsFeedViewModel(newsFeedViewModel:NewsViewModel): ViewModel
//    @IntoMap
//    @Binds
//    @ViewModelKey(OverviewViewModel::class)
//    abstract fun overviewViewModel(overviewViewModel: OverviewViewModel): ViewModel
}

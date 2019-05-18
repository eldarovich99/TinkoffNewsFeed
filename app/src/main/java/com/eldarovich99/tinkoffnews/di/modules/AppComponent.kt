package com.eldarovich99.tinkoffnews.di.modules

import com.eldarovich99.tinkoffnews.di.components.AppModule
import com.eldarovich99.tinkoffnews.di.components.ViewModelModule
import com.eldarovich99.tinkoffnews.presentation.MainActivity
import com.eldarovich99.tinkoffnews.presentation.newsfeed.NewsFeedFragment
import com.eldarovich99.tinkoffnews.presentation.overviewNews.OverviewNewsFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, ViewModelModule::class])
@Singleton
interface AppComponent{
    fun inject(activity: MainActivity)
    fun inject(fragment:OverviewNewsFragment)
    fun inject(fragment:NewsFeedFragment)
}
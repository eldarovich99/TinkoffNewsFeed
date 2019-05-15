package com.eldarovich99.tinkoffnews.di.modules

import com.eldarovich99.tinkoffnews.di.components.AppModule
import com.eldarovich99.tinkoffnews.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent{
    fun inject(activity: MainActivity)
}
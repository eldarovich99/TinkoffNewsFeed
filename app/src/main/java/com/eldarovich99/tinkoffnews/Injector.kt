package com.eldarovich99.tinkoffnews

import android.app.Application
import com.eldarovich99.tinkoffnews.di.components.AppModule
import com.eldarovich99.tinkoffnews.di.modules.AppComponent
import com.eldarovich99.tinkoffnews.di.modules.DaggerAppComponent

object Injector {
    private var appComponent: AppComponent? = null

    fun init(application: Application) {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(application)).build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent!!
    }
}
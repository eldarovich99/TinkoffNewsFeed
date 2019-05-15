package com.eldarovich99.tinkoffnews

import android.app.Application

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}
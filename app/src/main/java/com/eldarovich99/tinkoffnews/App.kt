package com.eldarovich99.tinkoffnews

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this)
    }
}
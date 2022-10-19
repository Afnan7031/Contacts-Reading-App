package com.example.contactdetailsapplication

import android.app.Application
import com.example.contactdetailsapplication.helpers.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        initDIKoin()
    }

    private fun initDIKoin() {
        startKoin {
            androidContext(this@ApplicationClass)
            modules(viewModelModule)
        }
    }
}

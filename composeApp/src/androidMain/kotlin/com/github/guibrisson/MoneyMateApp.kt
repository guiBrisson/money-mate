package com.github.guibrisson

import android.app.Application
import utils.initKoin
import org.koin.android.ext.koin.androidContext

class MoneyMateApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MoneyMateApp)
        }
    }
}

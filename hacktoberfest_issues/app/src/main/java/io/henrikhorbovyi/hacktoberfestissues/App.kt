package io.henrikhorbovyi.hacktoberfestissues

import android.app.Application
import io.henrikhorbovyi.hacktoberfestissues.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        koinSetup()
    }


    private fun koinSetup() {
        startKoin {
            modules(appModules)
            androidContext(applicationContext)
            androidLogger(Level.DEBUG)
        }
    }
}

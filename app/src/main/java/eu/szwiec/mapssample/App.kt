package eu.szwiec.mapssample

import android.app.Application
import eu.szwiec.mapssample.di.apiModule
import eu.szwiec.mapssample.di.mainModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        startKoin(this, listOf(mainModule, apiModule))
    }
}
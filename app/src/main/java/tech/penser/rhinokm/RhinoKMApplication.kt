package tech.penser.rhinokm

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import tech.penser.rhinokm.di.AppModules

class RhinoKMApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RhinoKMApplication)
            modules(AppModules.allModules)
        }
    }
}
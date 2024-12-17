package com.santimattius.basic.skeleton

import android.app.Application
import android.util.Log
import com.santimattius.android.first.module.FirstModuleServices
import com.santimattius.basic.skeleton.di.AppModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.runOnKoinStarted
import org.koin.dsl.KoinAppDeclaration
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module
import org.koin.mp.KoinPlatform

@OptIn(KoinExperimentalAPI::class)
class MainApplication : Application(), KoinStartup {

    private val coroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    override fun onKoinStartup(): KoinAppDeclaration = {
        androidContext(this@MainApplication)
        allowOverride(false)
        modules(AppModule().module)
        defaultModule()
    }

    override fun onCreate() {
        super.onCreate()
        coroutineScope.launch {
            val koin = KoinPlatform.getKoin()
            koin.runOnKoinStarted { koinStarted ->
                val service = koinStarted.get<FirstModuleServices>()
                Log.i("MainApplication", "Service: ${service.doSomething()}")
            }
        }
    }
}
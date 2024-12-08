package com.santimattius.android.first.module

import android.content.Context
import androidx.startup.Initializer
import org.koin.androix.startup.KoinInitializer
import org.koin.core.context.loadKoinModules

class FirstModuleInitializer : Initializer<Unit> {
    override fun create(context: Context) {
//        loadKoinModules(FirstModule().module)
        loadKoinModules(lazyFirstModule)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf(KoinInitializer::class.java)
    }
}
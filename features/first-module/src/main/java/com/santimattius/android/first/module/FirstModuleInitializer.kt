package com.santimattius.android.first.module

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.androix.startup.KoinInitializer
import org.koin.core.Koin
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.coroutine.KoinCoroutinesEngine
import org.koin.core.coroutine.KoinCoroutinesEngine.Companion.EXTENSION_NAME
import org.koin.core.extension.coroutinesEngine
import org.koin.core.module.Module
import org.koin.mp.KoinPlatformTools
import kotlin.time.measureTime

class FirstModuleInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        //loadLazyModules(lazyFirstModule)
        val time = measureTime {
            loadLazyModules(lazyFirstModule)
        }
        Log.d(this::class.simpleName, "create: $time")
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf(KoinInitializer::class.java)
    }
}

@OptIn(KoinInternalApi::class)
fun loadLazyModules(vararg moduleList: Lazy<Module>, dispatcher: CoroutineDispatcher? = null) {
    val koin = KoinPlatformTools.defaultContext().get()
    with(koin) {
        coroutinesEngine(dispatcher)
        koin.coroutinesEngine.launchStartJob {
            koin.loadModules(moduleList.map { it.value })
        }
    }
}

@OptIn(KoinInternalApi::class)
fun Koin.coroutinesEngine(dispatcher: CoroutineDispatcher? = null) {
    with(extensionManager) {
        if (getExtensionOrNull<KoinCoroutinesEngine>(EXTENSION_NAME) == null) {
            registerExtension(EXTENSION_NAME, KoinCoroutinesEngine(dispatcher))
        }
    }
}
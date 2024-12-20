package com.santimattius.android.first.module

import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.dsl.lazyModule


val lazyFirstModule = lazyModule {
    single { FirstModuleServices() }
}

@Module
class FirstModule{

    @Single
    fun provideFirstModuleServices(): FirstModuleServices {
        return FirstModuleServices()
    }
}